package tk.lmureu.robbery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String getText() {
		return ((EditText) findViewById(R.id.editText1)).getText().toString();
	}
	
	private void setText(String text) {
		((EditText) findViewById(R.id.editText1)).setText(text);
	}
	
	private void encrypt() {
		String text = ROT13OverBase64.encrypt(getText());
		setText(text);
		copy(text);
	}
	private void decrypt() {
		String text = ROT13OverBase64.decrypt(getText());
		setText(text);
		copy(text);
	}
	
	private void copy(String text){
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData data = ClipData.newPlainText("", text);
		clipboard.setPrimaryClip(data);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_encrypt:
				encrypt();
				return true;
			case R.id.action_decrypt:
				decrypt();
				return true;
			case R.id.action_about:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Robbery for Android\n"
						+ "\nThis program was originally written by Lorenzo Mureu in Python3 then ported to Android."
						+ "\nPlease enjoy."
						+ "\n\nemail: mureulor@gmail.com");
				builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				builder.show();
		}
		return super.onOptionsItemSelected(item);
	}
}
