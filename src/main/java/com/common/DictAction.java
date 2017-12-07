package com.common;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;
import chok.util.CollectionUtil;

@Scope("prototype")
@Controller
@RequestMapping("/dict")
public class DictAction extends BaseController<Object>
{
	@RequestMapping("/getCategorys")
	public void getCategorys()
	{
		List<Object> list = Dict.getCategorys(req.getParameterValueMap(false, true));
		printJson(list);
	}

	@RequestMapping("/getCategoryPage")
	public void getCategoryPage()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		result.put("total", Factory.getCategoryService().getCount(m));
		result.put("rows", Factory.getCategoryService().query(req.getDynamicSortParameterValueMap(m)));
		printJson(result.getData());
	}

	@RequestMapping("/getModels")
	public void getModels()
	{
		List<Object> list = Dict.getModels(req.getParameterValueMap(false, true));
		printJson(list);
	}

	@RequestMapping("/getModelPage")
	public void getModelPage()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		if (m.containsKey("category_ids[]"))
		{
			m.put("category_ids", CollectionUtil.toLongArray(req.getLongArray("category_ids[]", 0l)));
			m.remove("category_ids[]");
		}
		result.put("total", Factory.getModelService().getCount(m));
		result.put("rows", Factory.getModelService().query(req.getDynamicSortParameterValueMap(m)));
		printJson(result.getData());
	}
	
}
