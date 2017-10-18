package com.admin.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.ImageDao;
import com.admin.dao.ModelDao;
import com.admin.entity.Image;
import com.admin.entity.Model;

import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import chok.util.PropertiesUtil;

@Service
public class ModelService extends BaseService<Model,Long>
{
	@Autowired
	private ModelDao modelDao;
	@Autowired
	private ImageDao imageDao;

	@Override
	public BaseDao<Model,Long> getEntityDao() 
	{
		return modelDao;
	}
	
	public void delBatch(Long[] ids) throws IOException
	{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("model_ids", ids);
		List<Image> images = imageDao.query(m);
		if (images.size() > 0) 
		{
			// 批量物理删除图片文件
			for(Image po : images)
			{
				File f = new File(PropertiesUtil.getValue("image.upload.path")+po.getString("url"));
				if(f.exists()) FileUtils.forceDelete(f);
			}
			// 删除图片数据
			imageDao.delByModelIds(ids);
		}
		// 删除模型数据
		super.del(ids);
	}
}
