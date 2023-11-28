import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root',
})
export class CryptoService {
  // Ensure that the secret key is 32 bytes (256 bits) long
  private static secretKey: string = 'JqU#VY47!9r*HLhGc^zso$P1iRld^6#K';

  public static encrypt(data: string): string {
    const ciphertext = CryptoJS.AES.encrypt(data, CryptoJS.enc.Utf8.parse(this.secretKey), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    return ciphertext.toString();
  }

  public static decrypt(ciphertext: string): string {
    const bytes = CryptoJS.AES.decrypt(ciphertext, CryptoJS.enc.Utf8.parse(this.secretKey), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    const decryptedData = bytes.toString(CryptoJS.enc.Utf8);
    return decryptedData;
  }
}
