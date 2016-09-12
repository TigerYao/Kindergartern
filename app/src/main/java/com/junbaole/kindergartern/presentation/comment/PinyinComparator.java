package com.junbaole.kindergartern.presentation.comment;

import com.junbaole.kindergartern.data.model.UserInfo;

import java.util.Comparator;


public class PinyinComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		// 按照名字排序
		UserInfo UserInfo0 = (UserInfo) arg0;
		UserInfo UserInfo1 = (UserInfo) arg1;
		String catalog0 = "";
		String catalog1 = "";

		if (UserInfo0 != null && UserInfo0.name != null
				&& UserInfo0.name.length() > 1)
			catalog0 = PingYinUtil.converterToFirstSpell(UserInfo0.name)
					.substring(0, 1);

		if (UserInfo1 != null && UserInfo1.name != null
				&& UserInfo1.name.length() > 1)
			catalog1 = PingYinUtil.converterToFirstSpell(UserInfo1.name)
					.substring(0, 1);
		int flag = catalog0.compareTo(catalog1);
		return flag;

	}

}
