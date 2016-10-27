import java.util.Random;

public class Codec {

	static String coder(String origin) {
		StringBuilder encoded = new StringBuilder();
		StringBuilder preEncoded = new StringBuilder();

		for (int i = 0; i < origin.length(); i++) {
			int charCode = origin.charAt(i);
			preEncoded.append(String.valueOf(charCode).length()).append(charCode);
		}

		int[] key = generateKey(preEncoded.length());
		for (int i = 0; i < preEncoded.length(); i++) {
			int charCode = Character.getNumericValue(preEncoded.charAt(i));
			int secretValue = (int) '0' + charCode + key[i];
			encoded.append((char) secretValue).append(key[i]);
		}
		return encoded.toString();
	}

	static String decoder(String encoded) {
		StringBuilder decrypted = new StringBuilder();
		StringBuilder preDecrypted = new StringBuilder();
		int id = 0;

		for (int i = 0; i < encoded.length(); i += 2) {
			int unKey = encoded.charAt(i) - Character.getNumericValue(encoded.charAt(i + 1));
			preDecrypted.append(Character.getNumericValue((char) unKey));
		}

		while (id < preDecrypted.length()) {
			int lenght = Character.getNumericValue(preDecrypted.charAt(id));
			String decryptedSymbol = preDecrypted.substring(id + 1, id + lenght + 1);
			decrypted.append((char) Integer.parseInt(decryptedSymbol));
			id += (lenght + 1);
		}
		return decrypted.toString();
	}

	private static int[] generateKey(int length) {
		Random random = new Random();
		int keyDelta = 10;
		int[] key = new int[length];
		for (int m = 0; m < key.length; m++) {
			key[m] = random.nextInt(keyDelta);
		}
		return key;
	}

	public static void main(String[] args) throws Exception {
		String origin = "aa";
		System.out.println("Original string: " + origin);

		// coding
		String encoded = coder(origin);
		System.out.println("Secure strng: " + encoded);

		// decoding
		origin = decoder(encoded);
		System.out.println("Unsecure string: " + origin);

	}
}