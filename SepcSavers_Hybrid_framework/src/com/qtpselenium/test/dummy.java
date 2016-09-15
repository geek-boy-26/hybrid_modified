package com.qtpselenium.test;

import com.qtpselenium.xls.read.Xls_Reader;

public class dummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 Xls_Reader x1 = new Xls_Reader("D:\\Aakar\\Selenium\\SepcSavers_Hybrid_framework\\src\\com\\qtpselenium\\xls\\C Suite.xlsx");
 
 	String x = x1.getCellData("Test Steps", 1,386);
 	System.out.println(x);
 
	}

}
