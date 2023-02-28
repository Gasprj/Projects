package com.example.demo.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cripto {
	
	public static String crypt(String senha) throws NoSuchAlgorithmException {
		MessageDigest ms = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, ms.digest(senha.getBytes()));
		return hash.toString();
	}

}
