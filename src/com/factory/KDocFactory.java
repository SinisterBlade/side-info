package com.factory;

import java.sql.Connection;
import java.util.ArrayList;

public class KDocFactory {
	Connection con;
	ArrayList<String> vectors;
	public KDocFactory(Connection con, ArrayList<String> vectors) {
		this.con = con;
		this.vectors = vectors;
	}
}
