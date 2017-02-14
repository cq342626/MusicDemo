package com.chenqi.musicdemo.fragment;

import android.support.v4.app.Fragment;

/**
 * \
 *
 */
public class FragmentThree extends Fragment {
	
	
	private static FragmentThree localFragment = new FragmentThree();
	
	
	public static Fragment newInstance() {
		if(localFragment == null)
			localFragment = new FragmentThree();
		return localFragment;
	}

	
}
