package com.ontariotechu.sofe3980u;

public class Binary {
	private String number;

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String that represents a binary value
	 */
	public Binary(String number) {
		this.number = number;
	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables. For more information, visit
	 * <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		int carry = 0;
		String num3 = "";
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
			int sum = carry;
			if (ind1 >= 0) {
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}
			if (ind2 >= 0) {
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}
			carry = sum / 2;
			sum = sum % 2;
			num3 = ((sum == 0) ? "0" : "1") + num3;
		}
		return new Binary(num3);
	}

	/**
	 * Perform bitwise logical OR operation on two binary variables.
	 * OR operation returns 1 if at least one of the bits is 1.
	 *
	 * @param num1 The first binary operand
	 * @param num2 The second binary operand
	 * @return A binary variable with the result of <i>num1 OR num2</i>.
	 */
	public static Binary or(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		String paddedNum1 = padLeft(num1.number, maxLength);
		String paddedNum2 = padLeft(num2.number, maxLength);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char bit1 = paddedNum1.charAt(i);
			char bit2 = paddedNum2.charAt(i);
			result.append((bit1 == '1' || bit2 == '1') ? '1' : '0');
		}

		String resultStr = result.toString().replaceFirst("^0+(?!$)", "");
		return new Binary(resultStr.isEmpty() ? "0" : resultStr);
	}

	/**
	 * Perform bitwise logical AND operation on two binary variables.
	 * AND operation returns 1 only if both bits are 1.
	 *
	 * @param num1 The first binary operand
	 * @param num2 The second binary operand
	 * @return A binary variable with the result of <i>num1 AND num2</i>.
	 */
	public static Binary and(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		String paddedNum1 = padLeft(num1.number, maxLength);
		String paddedNum2 = padLeft(num2.number, maxLength);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char bit1 = paddedNum1.charAt(i);
			char bit2 = paddedNum2.charAt(i);
			result.append((bit1 == '1' && bit2 == '1') ? '1' : '0');
		}

		String resultStr = result.toString().replaceFirst("^0+(?!$)", "");
		return new Binary(resultStr.isEmpty() ? "0" : resultStr);
	}

	/**
	 * Multiply two binary variables using repeated addition.
	 * The algorithm uses the add function to accumulate the product.
	 *
	 * @param num1 The first multiplicand
	 * @param num2 The second multiplicand
	 * @return A binary variable with the result of <i>num1 × num2</i>.
	 */
	public static Binary multiply(Binary num1, Binary num2) {
		if (num1.number.equals("0") || num2.number.equals("0")) {
			return new Binary("0");
		}

		Binary result = new Binary("0");
		String multiplier = num2.number;
		for (int i = multiplier.length() - 1; i >= 0; i--) {
			if (multiplier.charAt(i) == '1') {
				int shift = multiplier.length() - 1 - i;
				String shiftedValue = num1.number + "0".repeat(shift);
				result = add(result, new Binary(shiftedValue));
			}
		}

		return result;
	}

	private static String padLeft(String str, int length) {
		if (str.length() >= length) {
			return str;
		}
		return "0".repeat(length - str.length()) + str;
	}
}