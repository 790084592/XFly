package com.xush.demo.action;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xush.demo.orm.filedatasource.FileDataSourceEntity;
import com.xush.demo.orm.filedatasource.FileDataSourceService;
import com.xush.demo.util.ResourceFunc;

/**
   *    数据源处理
 * 1.跳转到数据源界面
 * 2.跳转到文件数据源界面
 * 3.上传数据源文件
 * @author xush
 * @since  2019年9月19日
 */
@Controller
public class ActionDataSource {

	@Autowired
	private FileDataSourceService dsService;

	/**
	 * 跳转到数据源界面
	 * @return
	 */
	@RequestMapping("/datasource")
	public String toDataSource() {
		return "datasource/datasource";
	}

	/**
	 * 跳转到文件数据源界面
	 * @return
	 */
	@RequestMapping("/datasource/filelist")
	public String toFileListFrame() {
		return "datasource/filelist";
	}

	/**
	 * 上传数据源文件
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/datasource/uploadDataSource")
	@ResponseBody
	public String uploadDataSource(HttpServletRequest req) throws Exception {
		String type = req.getParameter("type"); //数据源类型
		String tag = req.getParameter("tag"); //后缀名
		String account = (String) req.getSession().getAttribute("account"); //用户账号
		InputStream in = req.getPart("file").getInputStream();
		try {
			int index;
			byte[] bytes = new byte[1024];
			String dsId = ResourceFunc.createDataSourceId(account, type); //资源ID
			String sortPath = ResourceFunc.getFileDataSourcePath(dsId, tag); //服务器后台存储路径
			FileOutputStream fos = new FileOutputStream(sortPath);
			try {
				while ((index = in.read(bytes)) != -1) {
					fos.write(bytes, 0, index);
					fos.flush();
				}
				DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String createDate = dayFormat.format(new Date());
				FileDataSourceEntity dsource = new FileDataSourceEntity(dsId, dsId, sortPath, 0, createDate, account);
				dsService.insert(dsource);
			} finally {
				fos.close();
			}
		} finally {
			in.close();
		}
		return "success";
	}

	/**
	 * 查询文件数据源列表
	 * @param req
	 * @return
	 */
	@RequestMapping("/datasource/listFileDataSource")
	@ResponseBody
	public HashMap<String, Object> listFileDataSource(HttpServletRequest req) {
		int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		HashMap<String, Object> result = new HashMap<String, Object>();
		int totalCouts = dsService.getTotalCounts();
		ArrayList<FileDataSourceEntity> list = dsService.listDatas(pageIndex, pageSize);
		result.put("total", totalCouts);
		result.put("rows", list);
		return result;
	}

}
