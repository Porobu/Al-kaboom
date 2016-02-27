package si.alkaboom.externals;

//SOURCE: http://openapex.org/howto/codepage.php?param=SmF2YSBTd2luZyQkJEhvdyB0byBlbmZvcmNlIEpUZXh0RmllbGQgdG8gYWxsb3cgb25seSBudW1lcmljIGlucHV0IGRhdGEgb2YgbGltaXRlZCBsZW5ndGg/JCQkSlRleHRGaWVsZCBvZiBsaW1pdGVkIGxlbmd0aCwgSlRleHRGaWVsZCByZXN0cmljdHMgbGVuZ3RoLCBKVGV4dEZpZWxkLCBudW1lcmljIGRhdGEsIEpUZXh0RmllbGQgYWxsb3dzIG9ubHkgbnVtZXJpYyxKVGV4dEZpZWxkIGFsbG93cyBvbmx5IG51bWJlcnMkJCRmaWxlcy9OdW1lcmljVGV4dEZpZWxkLmh0bWw=

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericAndLengthFilter extends DocumentFilter {

	/**
	 * Number of characters allowed.
	 */
	private int length = 0;

	/**
	 * Restricts the number of characters can be entered by given length.
	 * 
	 * @param length
	 *            Number of characters allowed.
	 */
	public NumericAndLengthFilter(int length) {
		this.length = length;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		if (isNumeric(string)) {
			if (this.length > 0 && fb.getDocument().getLength() + string.length() > this.length) {
				return;
			}
			super.insertString(fb, offset, string, attr);
		}
	}

	/**
	 * This method tests whether given text can be represented as number. This
	 * method can be enhanced further for specific needs.
	 * 
	 * @param text
	 *            Input text.
	 * @return {@code true} if given string can be converted to number;
	 *         otherwise returns {@code false}.
	 */
	private boolean isNumeric(String text) {
		if (text == null || text.trim().equals("")) {
			return false;
		}
		for (int iCount = 0; iCount < text.length(); iCount++) {
			if (!Character.isDigit(text.charAt(iCount))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		if (isNumeric(text)) {
			if (this.length > 0 && fb.getDocument().getLength() + text.length() > this.length) {
				return;
			}
			super.insertString(fb, offset, text, attrs);
		}
	}
}