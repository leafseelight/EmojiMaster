package com.mw.emoji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mw.emoji.utils.EmojiFilterTools;
import com.mw.emoji.utils.EmojiUtil;
import com.mw.emoji.view.EmojiEditText;
import com.mw.emoji.view.EmojiTextView;

public class MainActivity extends Activity {

	private EmojiEditText et_emoji;
	private EmojiTextView tv_emoji;
	private EditText et_emoji_filter;
	private TextView tv_result;
	private Button btn_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_emoji = (EmojiEditText) findViewById(R.id.et_emoji);
		tv_emoji = (EmojiTextView) findViewById(R.id.tv_emoji);// 原始字符串
		tv_result = (TextView) findViewById(R.id.tv_result);// 过滤后字符串
		btn_ok = (Button) findViewById(R.id.btn_ok);

		et_emoji_filter = (EditText) findViewById(R.id.et_emoji_filter);

		//禁用Emoji表情，并限制输入的字符数
		et_emoji_filter.setFilters(EmojiFilterTools.getInstance().InputFilterEdtMaxLength(12));
		//只禁用Emoji表情
//		et_emoji_filter.setFilters(EmojiFilterTools.getInstance().InputFilterEdt());

		btn_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_emoji.getText().toString())) {
					return;
				}
				tv_emoji.setText("原始结果为：" + et_emoji.getText().toString());
				Log.e("MainActivity", "原始结果为：" + et_emoji.getText().toString());
				if (EmojiUtil.containsEmoji(et_emoji.getText().toString())) {// 判断是否含有emojicon
					Toast.makeText(getApplicationContext(), "内容中含有emojicon表情",
							Toast.LENGTH_SHORT).show();
					tv_result.setText("过滤结果为："
							+ EmojiUtil.filterEmoji(et_emoji.getText()
									.toString()));
				} else {
					Toast.makeText(getApplicationContext(), "内容中不含emojicon表情",
							Toast.LENGTH_SHORT).show();
				}
			}

		});

	}

	public void showAboutMe(View view){
		startActivity(new Intent(this,AboutMeActivity.class));
	}

}
