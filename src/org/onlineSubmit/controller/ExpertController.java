package org.onlineSubmit.controller;
import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.onlineSubmit.entity.Distribute;
import org.onlineSubmit.entity.Expert;
import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.entity.User;
import org.onlineSubmit.page.Page;
import org.onlineSubmit.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/expert")
@Controller
public class ExpertController {
	@Autowired
	private ExpertService expertService;
	//跳转到系统主页-专家登录
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		Expert expert = expertService.findExpertByUserName(username);
		model.addObject("expert",expert);
		model.setViewName("expert/index");
		return model;
	}
	//跳转到查询稿件页面
	@RequestMapping(value="/queryManuscript",method=RequestMethod.GET)
	public ModelAndView queryManuscript(ModelAndView model) {
		model.setViewName("expert/queryManuscript");
		return model;
	}
	@RequestMapping(value="/get_list_by_expert",method=RequestMethod.POST)
	@ResponseBody
		public Map<String,Object> getList(Page page,@RequestParam(value="state",required=true,defaultValue="") String state){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
			page.setOffset();
			queryMap.put("offset",page.getOffset());
			queryMap.put("pageSize",page.getRows());
			if(state.equals("已审核")) {
				state = "通过";
			}
			if(state.equals("显示所有稿件")) {
				state = "";
			}
			queryMap.put("state","%"+state+"%");
			List<Distribute> list = expertService.findDistributeList();
			String ids = "";
			for(Distribute distribute : list) {
				ids += distribute.getDistribute_idstring()+",";
			}
			ids = ids.substring(0,ids.length()-1);
			queryMap.put("idstr",ids);
			List<Manuscript> distributeList = expertService.findManuscriptListByExpert(queryMap);
			for(Manuscript manuscript :  distributeList) {
				if(manuscript.getState().equals("已发布")) {
					manuscript.setState("已通过");
				}
				if(manuscript.getState().equals("已退回")) {
					manuscript.setState("已通过");
				}
			}
			ret.put("rows",distributeList);
			ret.put("total",expertService.getManuscriptTotalByExpert(queryMap));
			return ret;
		}
		//跳转到审核稿件页面
		@RequestMapping(value="/reviewManuscript",method=RequestMethod.GET)
		public ModelAndView reviewManuscript(ModelAndView model) {
			model.setViewName("expert/reviewManuscript");
			return model;
		}
		//专家查询待审核稿件
		@RequestMapping(value="/get_noReviewList_by_expert",method=RequestMethod.POST)
		@ResponseBody
			public Map<String,Object> get_noReviewList_by_expert(Page page){
			Map<String,Object> ret = new HashMap<String,Object>();
			Map<String,Object> queryMap = new HashMap<String,Object>();
				page.setOffset();
				queryMap.put("offset",page.getOffset());
				queryMap.put("pageSize",page.getRows());
				List<Distribute> list = expertService.findDistributeList();
				String ids = "";
				for(Distribute distribute : list) {
					ids += distribute.getDistribute_idstring()+",";
				}
				ids = ids.substring(0,ids.length()-1);
				queryMap.put("idstr",ids);
				queryMap.put("state","待审核");
				List<Manuscript> distributeList = expertService.findManuscriptNoReviewListByExpert(queryMap);
				ret.put("rows",distributeList);
				ret.put("total",expertService.getManuscriptTotalNoReviewByExpert(queryMap));
				return ret;
			}
		
		@RequestMapping(value="/download",method=RequestMethod.GET)
		public void download(HttpServletRequest request,HttpServletResponse response,String filePath) {
			String suffix = filePath.substring(filePath.indexOf("."),filePath.length());
			String forward = filePath.substring(0,filePath.indexOf("."));
			String prefix = forward.substring(forward.length()-13,forward.length());
			String filename = prefix+suffix;
			//String filename = "1585046223371.docx";//
				System.out.print(filename);
				//解决获得中文参数的乱码----下节课讲
				try {
					filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//美女.jpg
				String savePath = request.getServletContext().getRealPath("/")+"\\upload\\";
				//获得请求头中的User-Agent
				String agent = request.getHeader("User-Agent");
				//根据不同浏览器进行不同的编码
				String filenameEncoder = "";
				if (agent.contains("MSIE")) {
					// IE浏览器
					try {
						filenameEncoder = URLEncoder.encode(filename, "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					filenameEncoder = filenameEncoder.replace("+", " ");
				} else {
					// 其它浏览器
					try {
						filenameEncoder = URLEncoder.encode(filename, "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}



				//要下载的这个文件的类型-----客户端通过文件的MIME类型去区分类型
				//response.setContentType(this.getServletContext().getMimeType(filename));
				//告诉客户端该文件不是直接解析 而是以附件形式打开(下载)----filename="+filename 客户端默认对名字进行解码
				response.setHeader("Content-Disposition", "attachment;filename="+filenameEncoder);
				//获取文件的绝对路径
				String path = savePath+filename;
				//获得该文件的输入流
				InputStream in = null;
				try {
					in = new FileInputStream(path);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//获得输出流---通过response获得的输出流 用于向客户端写内容
				ServletOutputStream out = null;
				try {
					out = response.getOutputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//文件拷贝的模板代码
				int len = 0;
				byte[] buffer = new byte[1024];
				try {
					while((len=in.read(buffer))>0){
						try {
							out.write(buffer, 0, len);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//out.close();
			}
		@RequestMapping(value="/review",method=RequestMethod.GET)
		public void review(HttpServletRequest request,HttpServletResponse response,String flag,int manuscript_id) {
			Map<String,Object> updateMap = new HashMap<String,Object>();
			updateMap.put("manuscript_id",manuscript_id);
			if(flag.equals("true")) {
				updateMap.put("state","已通过");
			}
			if(flag.equals("false")) {
				updateMap.put("state","未通过");
			}
			if(expertService.editManuscriptByManuscript(updateMap) >= 0) {
				System.out.print("更新成功");
			}
			int expert_id = (Integer) request.getSession().getAttribute("expert_id");
			updateMap.put("expert_id",expert_id);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String format = sf.format(new Date());
			updateMap.put("expert_review_time",format);
			updateMap.put("expert_opinion", updateMap.get("state"));
			if(expertService.addExpertReviewInfo(updateMap) >=0 ) {
				System.out.println("插入数据成功！");
				try {
					response.sendRedirect("reviewManuscript");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//跳转到专家个人信息管理页面
		@RequestMapping(value="/queryOwnInfo",method=RequestMethod.GET)
		public ModelAndView queryOwnInfo(ModelAndView model) {
			model.setViewName("expert/queryOwnInfo");
			return model;
		}
		//处理专家查询个人信息请求
		@RequestMapping(value="/getOwnInfoByExpert",method=RequestMethod.POST)
		@ResponseBody
			public Map<String,Object> getOwnInfoByExpert(HttpServletRequest request,HttpServletResponse response){
			Map<String,Object> ret = new HashMap<String,Object>();
				int expert_id = (Integer) request.getSession().getAttribute("expert_id");
				Expert expert = expertService.queryOwnInfoByExpert(expert_id);
				List list = new ArrayList();
				list.add(expert);
				ret.put("rows",list);
				ret.put("total",1);
				return ret;
		}
		@RequestMapping(value="/editOwnInfoByExpert",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,String> editOwnInfoByExpert(Expert expert){
			Map<String,String> ret = new HashMap<String,String>();
			if(StringUtils.isEmpty(expert.getUsername())) {
				ret.put("type","error");
				ret.put("msg","用户名不能为空！");
				return ret;
			}
			if(StringUtils.isEmpty(expert.getPassword())) {
				ret.put("type","error");
				ret.put("msg","密码不能为空！");
				return ret;
			}
			if(StringUtils.isEmpty(expert.getExpert_name())) {
				ret.put("type","error");
				ret.put("msg","姓名不能为空！");
				return ret;
			}
			if(StringUtils.isEmpty(expert.getPhone_number())) {
				ret.put("type","error");
				ret.put("msg","电话号码不能为空！");
				return ret;
			}
			if(expert.getPhone_number().length()!=11) {
				ret.put("type","error");
				ret.put("msg","电话号码应为11位！");
				return ret;
			}
			/**
			 * 验证手机号码的正确性
			 */
			String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
			Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(expert.getPhone_number());
	        boolean isMatch = m.matches();
	        if (!isMatch) {
	        	ret.put("type","error");
				ret.put("msg","请输入正确的手机号");
				return ret;
	        }
			if(StringUtils.isEmpty(expert.getAddress())) {
				ret.put("type","error");
				ret.put("msg","居住地址不能为空！");
				return ret;
			}
			if(expertService.editOwnInfoByExpert(expert) <= 0) {
				ret.put("type","error");
				ret.put("msg","修改失败！");
				return ret;
			}
			ret.put("type","success");
			ret.put("msg","修改成功！");
			return ret;
		}
}
