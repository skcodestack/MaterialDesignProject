package github.skcodestack.util.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import android.annotation.SuppressLint;

public class DHUtil {
	public static String src = "hui security dh";
	
	@SuppressLint({ "NewApi", "TrulyRandom" })
	public static void jdkDH(){
		try {
			// 1.初始化发送方秘钥
			KeyPairGenerator sendKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			sendKeyPairGenerator.initialize(512);
			KeyPair sendKeyPair = sendKeyPairGenerator.generateKeyPair();
			// 发送方的公钥（网络文件，优盘拷贝）
			byte[] sendKeyPublicEnc = sendKeyPair.getPublic().getEncoded();
			
			// 2.初始化接收方的秘钥
			KeyFactory receiverKeyFactory  = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(sendKeyPublicEnc);
			PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
			DHParameterSpec dhParameterSpec = ((DHPublicKey)receiverPublicKey).getParams();
			KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			receiverKeyPairGenerator.initialize(dhParameterSpec);
			KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
			PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
			byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();
			
			// 3.秘钥构建
			KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
			receiverKeyAgreement.init(receiverPrivateKey);
			receiverKeyAgreement.doPhase(receiverPublicKey, true);
			SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");
			
			KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
			x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
			PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
			KeyAgreement sendKeyAgreement = KeyAgreement.getInstance("DH");
			sendKeyAgreement.init(sendKeyPair.getPrivate());
			sendKeyAgreement.doPhase(senderPublicKey, true);
			SecretKey senderDesKey = sendKeyAgreement.generateSecret("DES");
			
			// 4.加密
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
			byte[] result = cipher.doFinal(src.getBytes());
			
			// 5.解密
			cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
			result = cipher.doFinal(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
