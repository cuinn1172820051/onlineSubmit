package org.onlineSubmit.controller;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.onlineSubmit.dao.UserDao;
import org.onlineSubmit.entity.Manuscript;
import org.onlineSubmit.entity.Nav;
import org.onlineSubmit.entity.User;
import org.onlineSubmit.page.Page;
import org.onlineSubmit.service.AuthorService;
import org.onlineSubmit.service.EditorService;
import org.onlineSubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
/**
 * 以作者身份登录系统
 * @author Administrator
 *
 */
@RequestMapping("/author")
@Controller
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	@Autowired
	private EditorService editorService;
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request) {
		int id = (Integer) request.getSession().getAttribute("id");
		User user = authorService.queryOwnInfo(id);
		model.addObject("user",user);
		model.setViewName("author/index");
		return model;
	}
/**
 * 跳转到添加稿件页面
 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(ModelAndView model) {
		Map<String,Object> queryMap = new HashMap();
		List<Nav> list = editorService.findNavList(queryMap);
		model.addObject("dataList",list);
		model.setViewName("author/addManuscript");
		return model;
	}
	/**
	 * 处理用户上传稿件请求
	 * @return
	 */
	@RequestMapping(value="/upload_file",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> upload(Manuscript manuscript,MultipartFile manuscript_file,HttpServletRequest request,
			HttpServletResponse response
			) throws IOException{
		Map<String,String> ret = new HashMap<String,String>();
		if(StringUtils.isEmpty(manuscript.getAuthor_name())) {
			ret.put("type","error");
			ret.put("msg","作者姓名不能为空！");
			return ret;
		}
		String author_name = request.getSession().getAttribute("author_name").toString();
		if(!manuscript.getAuthor_name().equals(author_name)) {
			ret.put("type","error");
			ret.put("msg","请输入正确的姓名！");
			return ret;
		}
		if(StringUtils.isEmpty(manuscript.getTitle())) {
			ret.put("type","error");
			ret.put("msg","稿件标题不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(manuscript.getSummary())) {
			ret.put("type","error");
			ret.put("msg","稿件摘要不能为空！");
			return ret;
		}
		if(manuscript_file==null) {
			ret.put("type","error");
			ret.put("msg","文件没有选择");
			return ret;
		}
		if(manuscript_file.getSize() > 10485760){
			//文件没有选择
			ret.put("type", "error");
			ret.put("msg", "文件大小超过10M！");
			return ret;
		}
		String suffix = manuscript_file.getOriginalFilename().substring(manuscript_file.getOriginalFilename().lastIndexOf(".")+1,manuscript_file.getOriginalFilename().length());
		if(!"docx,doc,pdf".contains(suffix)) {
			ret.put("type","error");
			ret.put("msg","文件格式不正确！");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/")+"\\upload\\";
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()) {
			savePathFile.mkdir();
		}
		String fileName = new Date().getTime()+"."+suffix;
		ret.put("src",request.getServletContext().getContextPath() + "/upload/" + fileName);
		manuscript_file.transferTo(new File(savePath+fileName));
		manuscript.setFilePath(ret.get("src"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sf.format(new Date());
		manuscript.setSubmit_time(format);
		manuscript.setId((Integer)request.getSession().getAttribute("id"));
		manuscript.setState("待审核");
		if(authorService.add(manuscript)<=0) {
			ret.put("type","error");
			ret.put("msg","稿件添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "文件上传成功！");
		return ret;
	}
	/**
	 * 跳转到查询稿件的页面
	 */
	@RequestMapping(value="/queryManuscript",method=RequestMethod.GET)
	public ModelAndView queryManuscript(ModelAndView model) {
		model.setViewName("author/queryManuscript");
		return model;
	}
	/**
	 * 显示稿件数据列表
	 * @param manuscript
	 * @param manuscript_file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
		public Map<String,Object> getList(
				@RequestParam(value="state",required=true,defaultValue="") String state,
				Page page,
				HttpServletRequest request,HttpServletResponse response
				){
		Map<String,Object> ret = new HashMap<String,Object>();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		System.out.print(state);
			page.setOffset();
			queryMap.put("offset",page.getOffset());
			queryMap.put("pageSize",page.getRows());
			queryMap.put("state","%"+state+"%");
			int id = (Integer) request.getSession().getAttribute("id");
			queryMap.put("id",id);
			List<Manuscript> list = authorService.findListById(queryMap);
			ret.put("rows",list);
			ret.put("total",authorService.getTotal(queryMap));
			return ret;
		}
	/**
	 * 跳转到查询个人信息的页面
	 */
	@RequestMapping(value="/queryOwnInfo",method=RequestMethod.GET)
	public ModelAndView queryOwnInfo(ModelAndView model) {
		model.setViewName("author/queryOwnInfo");
		return model;
	}
	/**
	 * 处理用户查询个人信息请求
	 * @param author_name
	 * @param title
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/getOwnInfo",method=RequestMethod.POST)
	@ResponseBody
		public Map<String,Object> getOwnInfo(
				HttpServletRequest request,HttpServletResponse response
				){
		Map<String,Object> ret = new HashMap<String,Object>();
			int id = (Integer) request.getSession().getAttribute("id");
			User user = authorService.queryOwnInfo(id);
			List<User> list = new ArrayList();
			list.add(user);
			ret.put("rows",list);
			ret.put("total",1);
			return ret;
		}
	/**
	 * 跳转到修改个人信息的页面
	 */
	@RequestMapping(value="/editOwnInfo",method=RequestMethod.GET)
	public ModelAndView editOwnInfo(ModelAndView model) {
		model.setViewName("author/editOwnInfo");
		return model;
	}
	@RequestMapping(value="/editOwnInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> register(User user){
		Map<String,String> ret = new HashMap<String,String>();
		if(StringUtils.isEmpty(user.getUsername())) {
			ret.put("type","error");
			ret.put("msg","用户名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type","error");
			ret.put("msg","密码不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getAuthor_name())) {
			ret.put("type","error");
			ret.put("msg","姓名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPhone_number())) {
			ret.put("type","error");
			ret.put("msg","电话号码不能为空！");
			return ret;
		}
		if(user.getPhone_number().length()!=11) {
			ret.put("type","error");
			ret.put("msg","电话号码应为11位！");
			return ret;
		}
		/**
		 * 验证手机号码的正确性
		 */
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(user.getPhone_number());
        boolean isMatch = m.matches();
        if (!isMatch) {
        	ret.put("type","error");
			ret.put("msg","请输入正确的手机号");
			return ret;
        }
		if(StringUtils.isEmpty(user.getAddress())) {
			ret.put("type","error");
			ret.put("msg","居住地址不能为空！");
			return ret;
		}
		if(authorService.editOwnInfo(user) <= 0) {
			ret.put("type","error");
			ret.put("msg","修改失败！");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg","修改成功！");
		return ret;
	}
	
	
	
	
	
	
}
