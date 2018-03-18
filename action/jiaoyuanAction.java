package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TJiaoyuanDAO;
import com.dao.TXueyuanDAO;
import com.model.TJiaoyuan;
import com.model.TXueyuan;
import com.opensymphony.xwork2.ActionSupport;

public class jiaoyuanAction extends ActionSupport
{
	private int id;
	private String loginname;
	private String loginpw;
	private String name;
	private String sex;
	private String age;
	private String tel;
	private String address;
	
	private String shenfen;
	private String xueli;
	private String kefudaokemu;
	private String ziwojieshao;
	private String fujian;
	
	private String message;
	private String path;
	
	private TJiaoyuanDAO jiaoyuanDAO;
	
	
	public String jiaoyuanLogout()
	{
		Map session= ServletActionContext.getContext().getSession();
		session.remove("jiaoyuan");
		session.remove("userType");
		return ActionSupport.SUCCESS;
	}
	
	
	public String jiaoyuanAdd()
	{
		TJiaoyuan jiaoyuan=new TJiaoyuan();
		jiaoyuan.setLoginname(loginname);
		jiaoyuan.setLoginpw(loginpw);
		jiaoyuan.setName(name);
		jiaoyuan.setSex(sex);
		jiaoyuan.setAge(age);
		jiaoyuan.setTel(tel);
		jiaoyuan.setAddress(address);
		jiaoyuan.setShenfen(shenfen);
		jiaoyuan.setXueli(xueli);
		jiaoyuan.setKefudaokemu(kefudaokemu);
		jiaoyuan.setZiwojieshao(ziwojieshao);
		jiaoyuan.setDel("shenhezhong");
		jiaoyuan.setFujian(fujian.equals("")==true?"/img/none.gif":fujian);
		jiaoyuanDAO.save(jiaoyuan);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "注册成功。等待管理员审核");
		return "successAdd";
		
	}
	
	
	public String jiaoyuanMana()
	{
		String sql="from TJiaoyuan where del !='yes' order by del";
		List jiaoyuanList=jiaoyuanDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("jiaoyuanList", jiaoyuanList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String jiaoyuanDel()
	{
		String sql="update TJiaoyuan set del='yes' where id="+id;
		jiaoyuanDAO.getHibernateTemplate().bulkUpdate(sql);
		this.setMessage("删除完毕");
		this.setPath("jiaoyuanMana.action");
		return "succeed";
	}
	
	public String jiaoyuanDetail()
	{
		TJiaoyuan jiaoyuan=jiaoyuanDAO.findById(id);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("jiaoyuan", jiaoyuan);
		return ActionSupport.SUCCESS;
	}
	
	
	public String jiaoyuanShenhe()
	{
		String sql="update TJiaoyuan set del='no' where id="+id;
		jiaoyuanDAO.getHibernateTemplate().bulkUpdate(sql);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "审核成功");
		return "msg";
	}
	
	public String getFujian()
	{
		return fujian;
	}


	public void setFujian(String fujian)
	{
		this.fujian = fujian;
	}


	public String jiaoyuanDetail_qian()
	{
		TJiaoyuan jiaoyuan=jiaoyuanDAO.findById(id);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("jiaoyuan", jiaoyuan);
		return ActionSupport.SUCCESS;
	}

	
	public String jiaoyuanAll()
	{
		String sql="from TJiaoyuan where del='no'";
		List jiaoyuanList=jiaoyuanDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("jiaoyuanList", jiaoyuanList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String jiaoyuanSearch()
	{
		String sql="from TJiaoyuan where del='no' and kefudaokemu like '%"+kefudaokemu.trim()+"%'";

		List jiaoyuanList=jiaoyuanDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("jiaoyuanList", jiaoyuanList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String jiaoyuanEdit()
	{
		TJiaoyuan jiaoyuan=jiaoyuanDAO.findById(id);
		jiaoyuan.setLoginname(loginname);
		jiaoyuan.setLoginpw(loginpw);
		jiaoyuan.setName(name);
		jiaoyuan.setSex(sex);
		jiaoyuan.setAge(age);
		jiaoyuan.setTel(tel);
		jiaoyuan.setAddress(address);
		jiaoyuan.setShenfen(shenfen);
		jiaoyuan.setXueli(xueli);
		jiaoyuan.setKefudaokemu(kefudaokemu);
		jiaoyuan.setZiwojieshao(ziwojieshao);
		jiaoyuan.setFujian(fujian);
		jiaoyuanDAO.getHibernateTemplate().update(jiaoyuan);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("msg", "修改成功");
		return "successAdd";
		
	}
	
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getKefudaokemu()
	{
		return kefudaokemu;
	}

	public void setKefudaokemu(String kefudaokemu)
	{
		this.kefudaokemu = kefudaokemu;
	}

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}

	public String getLoginpw()
	{
		return loginpw;
	}

	public void setLoginpw(String loginpw)
	{
		this.loginpw = loginpw;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getShenfen()
	{
		return shenfen;
	}

	public void setShenfen(String shenfen)
	{
		this.shenfen = shenfen;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getXueli()
	{
		return xueli;
	}

	public void setXueli(String xueli)
	{
		this.xueli = xueli;
	}


	public TJiaoyuanDAO getJiaoyuanDAO()
	{
		return jiaoyuanDAO;
	}

	public void setJiaoyuanDAO(TJiaoyuanDAO jiaoyuanDAO)
	{
		this.jiaoyuanDAO = jiaoyuanDAO;
	}

	public String getZiwojieshao()
	{
		return ziwojieshao;
	}

	public void setZiwojieshao(String ziwojieshao)
	{
		this.ziwojieshao = ziwojieshao;
	}

}
