package bijznas.notify.crouton;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.technorio.inc.bloodbankplus.R;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ShowInfo extends DialogFragment implements OnClickListener {
	int id;
	Button ok;
	Dialog dialog;
	ImageView photo;
	Bitmap bitmap = null;
	FragmentManager manager;
	Context context;
	private File mFileTemp;
	TextView topdf;
	ListView lv;
	String info = "";
	ArrayList<String> information = new ArrayList<String>();
	String[] caption = null;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.show_info);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		context = getActivity();
		manager = getActivity().getSupportFragmentManager();
		lv = (ListView) dialog.findViewById(R.id.lVDetails);
		topdf = (TextView) dialog.findViewById(R.id.topdf);
		photo =(ImageView)dialog.findViewById(R.id.imageView3);
		MyCustomAdapter adapterCustom = new MyCustomAdapter(context, caption,
				information);
		lv.setAdapter(adapterCustom);
		ok = (Button) dialog.findViewById(R.id.btnOkSUP);

		ok.setOnClickListener(this);
		topdf.setText(info + " Found");

		// read();
		// Message.message(getActivity(), alContacts.get(0));
		dialog.show();

		return dialog;
	}

	public void setup(String url, String licno, String holder, String issue, String expiry,
			String citi, String dob, String bg, String fn, String add,
			String issued_by, String cat, String no_off) {
		information.clear();
		caption = null;
		// Create an object for subclass of AsyncTask
		GetXMLTask task = new GetXMLTask();
		// Execute the task
		task.execute(new String[] { url });
//		information.add(url);
		information.add(licno);
		information.add(holder);
		information.add(issue);
		information.add(expiry);
		information.add(citi);
		information.add(dob);
		information.add(bg);
		information.add(fn);
		information.add(add);
		information.add(issued_by);
		information.add(cat);
		information.add(no_off);
		caption = new String[] { "License No.", "License Holder", "Issue Date",
				"Expiry Date", "Citizenship Number", "DOB", "Blood Group",
				"Fathers Name", "Address", "Issued By", "Category",
				"No. of offense" };
		info = "License No." + licno;
	}
	private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap map = null;
			for (String url : urls) {
				map = downloadImage(url);
			}
			return map;
		}

		// Sets the Bitmap returned by doInBackground
		@Override
		protected void onPostExecute(Bitmap result) {
			photo.setImageBitmap(result);
		}

		// Creates Bitmap from InputStream and returns it
		private Bitmap downloadImage(String url) {
			Bitmap bitmap = null;
			InputStream stream = null;
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			bmOptions.inSampleSize = 1;

			try {
				stream = getHttpConnection(url);
				bitmap = BitmapFactory.
						decodeStream(stream, null, bmOptions);
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return bitmap;
		}

		// Makes HttpURLConnection and returns InputStream
		private InputStream getHttpConnection(String urlString)
				throws IOException {
			InputStream stream = null;
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();

			try {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod("GET");
				httpConnection.connect();

				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					stream = httpConnection.getInputStream();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return stream;
		}
	}

	//p_cap,p_owner,use,area,custom
	public void setupBlueBook(String url, String bn, String vn, String ty, String iat,
			String edt, String owner, String nati, String eby,String company,String model
	,String year,String c_no,String horse,String chassis,String engine,String color,
							  String petrol,String cap,String powner,String use,
							  String area,String custom) {

		information.clear();
		caption = null;
		// Create an object for subclass of AsyncTask
		GetXMLTask task = new GetXMLTask();
		// Execute the task
		task.execute(new String[] { url });
		information.add(bn);
		information.add(vn);
		information.add(ty);
		information.add(iat);
		information.add(edt);
		information.add(owner);
		information.add(nati);
		information.add(eby);
		information.add(company);
		information.add(model);
		information.add(year);
		information.add(c_no);
		information.add(horse);
		information.add(chassis);
		information.add(engine);
		information.add(color);
		information.add(petrol);
		information.add(cap);
		information.add(powner);
		information.add(use);
		information.add(area);
		information.add(custom);
		caption = new String[] { "BillBook No.", "Vehicle No.", "Type",
				"Issued at", "Expiry date", "Owner", "Nationality", "Issued by", "Company Name","Vehicle Model",
				"Established Year", "Number of Cylinder","Horse Power/cc","Chassis Number",
		"Engine Number","Vehicle Color","Petrol/Diseal","Passenger Capacity","Previous Owner/Company",
		"Use","Permitted Area","Custom Details"};
		info = "BillBook No." + bn;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.btnOkSUP) {

			dialog.dismiss();
		}
	}



}