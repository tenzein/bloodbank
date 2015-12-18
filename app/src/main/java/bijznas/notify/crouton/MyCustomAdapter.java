package bijznas.notify.crouton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.technorio.inc.bloodbankplus.R;

import java.util.ArrayList;
import java.util.Locale;

public class MyCustomAdapter extends BaseAdapter {
	private Context context;
	String[] infoss_cap ;
	ArrayList<String> information = new ArrayList<String>();
	// HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	private static LayoutInflater inflater = null;

	TextView tvLeft, tvRight;

	// int imgRes = R.drawable.candle;

	public MyCustomAdapter(Context a, String[] caption, ArrayList<String> information) {
		// super.MyCustomAdapter(a, textViewResourceId, s);
		// TODO Auto-generated constructor stub
		// noti = not;
		context = a;
		this.information = information;
		infoss_cap = caption;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		final int pos = position;

		if (convertView != null)
			vi = convertView;
		else {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = infalInflater.inflate(R.layout.listitem_signup, null);
		}

		tvLeft = (TextView) vi.findViewById(R.id.tVLeft);
		tvRight = (TextView) vi.findViewById(R.id.tVRight);
		tvLeft.setText(infoss_cap[position].toUpperCase(Locale.ENGLISH));
		tvRight.setText(information.get(position));

		return vi;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return information.size();
	}

}