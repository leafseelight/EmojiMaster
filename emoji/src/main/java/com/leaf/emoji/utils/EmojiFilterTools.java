package com.leaf.emoji.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiFilterTools {

    private EmojiFilterTools() {
    }

    private static class ToolsInstance {
        private static final EmojiFilterTools INSTANCE = new EmojiFilterTools();
    }

    public static EmojiFilterTools getInstance() {
        return ToolsInstance.INSTANCE;
    }



    /**
     * 间隔500ms 点击
     *
     * @return
     */
    private long lastClickTime;

    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return (0 < timeD && timeD <= 300);
    }


    /**
     * @return InputFilter[]    返回类型
     * @Title: InputFilterEdt
     * @Description: (过滤 表情)
     */
    public InputFilter[] InputFilterEdt() {
        InputFilter emojiFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }

        };
        return new InputFilter[]{emojiFilter};

    }

    /**
     * 设置 限制文字长度
     *
     * @param maxLength
     * @return
     */
    public InputFilter[] InputFilterEdtMaxLength(int maxLength) {
        return InputFilterEdt(new InputFilter.LengthFilter(maxLength));
    }

    /**
     * @param
     * @return InputFilter[]    返回类型
     * @Title: InputFilterEdt
     * @Description: (过滤 表情 +自定义过滤规则)
     */
    public InputFilter[] InputFilterEdt(InputFilter mInputFilter) {
        InputFilter emojiFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }

        };
        return new InputFilter[]{mInputFilter, emojiFilter};

    }


}
