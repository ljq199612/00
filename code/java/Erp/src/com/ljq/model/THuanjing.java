package com.ljq.model;

/**
 * THuanjing generated by MyEclipse Persistence Tools
 */

public class THuanjing implements java.io.Serializable
{

	// Fields

	private Integer id;

	private String bianhao;

	private String mingcheng;

	private String weizhi;

	private String mianji;

	private String shijian;

	private String jinian;
	private String del;

	// Constructors

	/** default constructor */
	public THuanjing()
	{
	}

	/** full constructor */
	public THuanjing(String bianhao, String mingcheng, String weizhi,
			String mianji, String shijian, String jinian)
	{
		this.bianhao = bianhao;
		this.mingcheng = mingcheng;
		this.weizhi = weizhi;
		this.mianji = mianji;
		this.shijian = shijian;
		this.jinian = jinian;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public String getDel()
	{
		return del;
	}

	public void setDel(String del)
	{
		this.del = del;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getBianhao()
	{
		return this.bianhao;
	}

	public void setBianhao(String bianhao)
	{
		this.bianhao = bianhao;
	}

	public String getMingcheng()
	{
		return this.mingcheng;
	}

	public void setMingcheng(String mingcheng)
	{
		this.mingcheng = mingcheng;
	}

	public String getWeizhi()
	{
		return this.weizhi;
	}

	public void setWeizhi(String weizhi)
	{
		this.weizhi = weizhi;
	}

	public String getMianji()
	{
		return this.mianji;
	}

	public void setMianji(String mianji)
	{
		this.mianji = mianji;
	}

	public String getShijian()
	{
		return this.shijian;
	}

	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	}

	public String getJinian()
	{
		return this.jinian;
	}

	public void setJinian(String jinian)
	{
		this.jinian = jinian;
	}

}