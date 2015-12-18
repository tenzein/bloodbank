package bijznas.notify.crouton;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.technorio.inc.bloodbankplus.R;

import java.util.ArrayList;

public class AlertDialog extends DialogFragment {
	//String name[];
	//String title, location, phoneno, email, fax, pob;
	String alertMessage;
	//String phone;
	String name = "";
	ArrayList<String> alContactsNumber = new ArrayList<String>();
	TextView message;
	//Spinner contacts;
	Button ok;
	private String style = "";
	ArrayList<String> alContactsName = new ArrayList<String>();
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if(savedInstanceState != null){
			alertMessage = savedInstanceState.getString("allertmesg");
		}
		final Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.alert_dialog);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		message = (TextView) dialog.findViewById(R.id.dialogMessage);
		ok = (Button) dialog.findViewById(R.id.buttonsay);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		if(style.equals("succ")){
			
			message.setText(Html.fromHtml("<b>Registration Successful</b><br>Congratulations "
					+ name
					+ ", your sign up was successful with  \"<b>"
					+ alertMessage + "</b>\".<br>Thank You."));
			message.setPadding(5, 5, 5, 5);
		}else{
			message.setText(alertMessage);
		}
		
		dialog.show();

		return dialog;
	}
	@Override
	public void onSaveInstanceState(Bundle arg0) {
		// TODO Auto-generated method stub
		arg0.putString("allertmesg", alertMessage);
	}
	public void setValue(String message) {
		// TODO Auto-generated method stub
		alertMessage = message;
		
	}
	public void setStyle(String message, String uname, String email) {
		// TODO Auto-generated method stub
		style = message;
		alertMessage = email;
		name = uname;
		
	}



}
