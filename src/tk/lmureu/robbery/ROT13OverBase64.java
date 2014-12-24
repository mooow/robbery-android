package tk.lmureu.robbery;

import android.util.Base64;

public class ROT13OverBase64 {

	static String encrypt(String text){
		String data = rot13(text);
		return Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
	}
	
	static String decrypt(String text){
		try {
			String data = new String( Base64.decode(text, Base64.DEFAULT) );
			return rot13(data);
		} catch (IllegalArgumentException ex) {
			return "";
		}
	}
	
	static String rot13 (String text) {
		final String start = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String end   = "nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM";
		String res = "";
		for (char c : text.toCharArray()) {
			int idx = start.indexOf(c);
			if (idx!=-1) {
				res += end.charAt(idx);
			} else {
				res += c;
			}
		}
		return res;
	}
}
