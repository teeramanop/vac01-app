package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Util {
    public static Locale fromString(String locale) {
        String parts[] = locale.split("_", -1);
        if (parts.length == 1) return new Locale(parts[0]);
            else if (parts.length == 2
                || (parts.length == 3 && parts[2].startsWith("#")))
            return new Locale(parts[0], parts[1]);
        else return new Locale(parts[0], parts[1], parts[2]);
    }
    public static String TodayDate(String dateFormat) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,fromString("en_US"));
        return sdf.format(cal.getTime());
    }
    public static String DateToString(Date aDate,String dateFormat) {
        if (aDate==null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,fromString("en_US"));
        return sdf.format(aDate);
    }
    
    public static String DateToString(Date aDate,String dateFormat,String local) {
        if (aDate==null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,fromString(local));
        return sdf.format(aDate);
    }

    public static Date StringToDate(String strDate,String dateFormat) {
        Date aDate = null;
        try {
            SimpleDateFormat df=new SimpleDateFormat(dateFormat,fromString("en_US"));
            aDate=df.parse(strDate);
            return aDate; 
        }
        catch (Exception err) {
            return null;
        }
     }
    
    public static int DayOfWeek(String strDate,String dateFormat) {
        Date aDate = null;
        try {
            SimpleDateFormat df=new SimpleDateFormat(dateFormat,fromString("en_US"));
            aDate=df.parse(strDate);
//            DateFormat format2=new SimpleDateFormat("EEEE",fromString("en_US")); 
//            String finalDay=format2.format(aDate);            
//            return finalDay; 
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.setTime(aDate);
            return calendar.get(Calendar.DAY_OF_WEEK);

        }
        catch (Exception err) {
            return 9;
        }
     }

    public static int WeekOfYear(String strDate,String dateFormat) {
        Date aDate = null;
        try {
            SimpleDateFormat df=new SimpleDateFormat(dateFormat,fromString("en_US"));
            aDate=df.parse(strDate);
//            DateFormat format2=new SimpleDateFormat("EEEE",fromString("en_US")); 
//            String finalDay=format2.format(aDate);            
//            return finalDay; 
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.setTime(aDate);
            return calendar.get(Calendar.WEEK_OF_YEAR);

        }
        catch (Exception err) {
            return 9;
        }
     }

    public static String BeDateString(String strDate) {
        if (strDate==null) {
            return "";
        }
        // Buddhist-Era
        String sDate = "";
        if (strDate.length()!=10) {
            return sDate;
        }
        int y = Integer.valueOf(strDate.substring(0, 4));
        if ((y-543)<1900) {
            y = y+543;
        }
        String yyyy = Integer.valueOf(y).toString().trim();
        sDate = yyyy + strDate.substring(4, 10);
        return sDate;
    }

    public static String CeDateString(String strDate) {
        if (strDate==null) {
            return "";
        }
        // Chistian-Era
        String sDate = "";
        if (strDate.length()!=10) {
            return sDate;
        }
        int y = Integer.valueOf(strDate.substring(0, 4));
        if ((y-543)>=1900) {
            y = y-543;
        }
        String yyyy = Integer.valueOf(y).toString().trim();
        sDate = yyyy + strDate.substring(4, 10);
        return sDate;
    }

    public static Date addMinute(Date aDate,int minToAdd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(Calendar.MINUTE, minToAdd);
        return cal.getTime();
    }

    public static String removeChar(String s, char c) {
       if (s == null) {
           return "0";
       }
       String r = "";
       for (int i = 0; i < s.length(); i ++) {
	  if (s.charAt(i) != c) r += s.charAt(i);
	  }
       return r;
   }
    public static String TimeDiff(String sDate,String eDate) {
        int  tt = 0;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss",fromString("en_US"));
	try {
            Date d1 = format.parse(sDate);	
            Date d2 = format.parse(eDate);	
            tt = (int) (d2.getTime() - d1.getTime());
            int hh = (int) tt/1000/60/60;
            tt = tt - (hh*1000*60*60);
            int mm = (int) tt/1000/60;
            tt = tt - (mm*1000*60);
            int ss = tt;
            return Integer.valueOf(hh).toString().trim()+":"+Integer.valueOf(mm).toString().trim()+":"+Integer.valueOf(ss).toString().trim();
	}
	catch(Exception e) {
            return "";
	}    
    }
    public static double DayDiff(String sDate,String eDate) {
        double i = 0;
	int x = 1000 * 60 * 60 * 24;
//		  String sDate = "2006-10-01";
//		  String eDate = "2006-10-10";
//	sDate = Util.cnvYYYYMMDD(sDate);	
//	eDate = Util.cnvYYYYMMDD(eDate);	

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",fromString("en_US"));
	try {
//           Date myDate = new Date();		
            Date d1 = format.parse(sDate);	
            Date d2 = format.parse(eDate);	
//          System.out.println(d1.toString());
//          System.out.println(d2.toString());
            i = (double) ((d2.getTime() - d1.getTime()) / (x));
	}
	catch(Exception e) {
            return 0;
	}    
	return i;
    }

    public static String AddDate(String YYYYMMDD,int days) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",fromString("en_US"));
        Calendar c = Calendar.getInstance();
        c.setTime(StringToDate(YYYYMMDD, "yyyy-MM-dd"));
        c.add(Calendar.DATE, days); 
        return sdf.format(c.getTime());
        
//        if (days < 0) {
//            // subtract
//            return SubDate(YYYYMMDD, days);
//        }
//        
//        String ndate = YYYYMMDD;
//        if (ndate.length()!=10) {
//            return "";
//        }
//        int year = Integer.valueOf(YYYYMMDD.substring(0, 4));
//        int month = Integer.valueOf(YYYYMMDD.substring(5, 7));
//        int day = Integer.valueOf(YYYYMMDD.substring(8, 10))+days;
//        while (day > 31) {
//            if (day > 31) {
//               if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
//                  day = day-31;
//                  month = month+1;
//                  if (month > 12) {
//                      month = 1;
//                      year = year+1;
//                  }
//               }
//            }
//            if (day > 30) {
//               if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
//                  day = day-30;
//                  month = month+1;
//                  if (month > 12) {
//                     month = 1;
//                     year = year+1;
//                  }
//               }
//            }
//            if (day > 29) {
//               if (month == 2) {
//                  day = day-29;
//                  month = month+1;
//                  if (month > 12) {
//                      month = 1;
//                      year = year+1;
//                  }
//               }
//            }
//        }
//        if (day > 30) {
//           if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
//              day = day-30;
//              month = month+1;
//              if (month > 12) {
//                 month = 1;
//                 year = year+1;
//              }
//           }
//        }
//        if (day > 29) {
//           if (month == 2) {
//              day = day-29;
//              month = month+1;
//              if (month > 12) {
//                 month = 1;
//                 year = year+1;
//              }
//           }
//        }
//        if (day==0) {
//            month = month -1;
//            if (month==0) {
//                month = 12;
//                year = year -1;
//            }
//            if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
//                day = 31;
//            }
//            if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
//                day = 30;
//            }
//            if (month == 2) {
//                day = 29;
//            }
//        }
//        
//        String dd = "00" + Integer.toString(day).trim();
//        dd = dd.substring(dd.length()-2, dd.length());
//
//        String mm = "00" + Integer.toString(month).trim();
//        mm = mm.substring(mm.length()-2,mm.length());
//
//        String yyyy = Integer.toString(year).trim();
//
//        ndate = yyyy + "-" + mm + "-" + dd;
//        return ndate;
    }
    
//    public static String SubDate(String YYYYMMDD,int days) {
//        String ndate = YYYYMMDD;
//        if (ndate.length()!=10) {
//            return "";
//        }
//        int year = Integer.valueOf(YYYYMMDD.substring(0, 4));
//        int month = Integer.valueOf(YYYYMMDD.substring(5, 7));
//        int day = Integer.valueOf(YYYYMMDD.substring(8, 10))+days;
//        while (day < 1) {
//            if (day < 1) {
//               if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
//                  day = day+31;
//               }
//               if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
//                  day = day+30;
//               }
//               if (month == 2) {
//                  day = day+29;
//               }
//               month = month-1;
//               if (month < 1) {
//                   month = 12;
//                   year = year-1;
//               }
//            }
//        }
//        
//        String dd = "00" + Integer.toString(day).trim();
//        dd = dd.substring(dd.length()-2, dd.length());
//
//        String mm = "00" + Integer.toString(month).trim();
//        mm = mm.substring(mm.length()-2,mm.length());
//
//        String yyyy = Integer.toString(year).trim();
//
//        ndate = yyyy + "-" + mm + "-" + dd;
//        return ndate;
//    }

    public static int dayOfWeek(Date aDate) {
        int dow = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);

        dow = cal.get(Calendar.DAY_OF_WEEK);
        
        return dow;
    }

    public static String cnvDDMMYYYY(String YYYYMMDD) {
        if (YYYYMMDD==null){
            return "";
        }
	if (YYYYMMDD.length()<10) {
            return YYYYMMDD;	
	}
        String DDMMYYYY = "";
        DDMMYYYY = YYYYMMDD.substring(8,10) + "-" + YYYYMMDD.substring(5,7) + "-" + YYYYMMDD.substring(0,4);		
        return DDMMYYYY;
   }
    
   public static String cnvDD_MM_YYYY(String YYYYMMDD) {
        if (YYYYMMDD==null){
            return "";
        }
	if (YYYYMMDD.length()<10) {
            return YYYYMMDD;	
	}
        String DDMMYYYY = "";
        DDMMYYYY = YYYYMMDD.substring(8,10) + "/" + YYYYMMDD.substring(5,7) + "/" + YYYYMMDD.substring(0,4);		
        return DDMMYYYY;
   } 

   public static String cnvYYYYMMDD(String DDMMYYYY) {
		if (DDMMYYYY.length()<10) {
		 return DDMMYYYY;	
		}
	   String YYYYMMDD = "";
	   YYYYMMDD = DDMMYYYY.substring(6,10) + "-" + DDMMYYYY.substring(3,5) + "-" + DDMMYYYY.substring(0,2);		
	   return YYYYMMDD;
    }
   public static double round(double d, int decimalPlace){
    // see the Javadoc about why we use a String in the constructor
    // http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
       BigDecimal bd = new BigDecimal(Double.toString(d));
    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
    return bd.doubleValue();
   }

   public static void copyfile(String srFile, String dtFile){
        try{
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f1);

            //For Append the file.
            //  OutputStream out = new FileOutputStream(f2,true);

            //For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage() + " in the specified directory.");
            //System.exit(0);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deletefile(String fileName){
        //String fileName = "file.txt";
        // A File object to represent the filename
        File f = new File(fileName);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists())
            throw new IllegalArgumentException(
          "Delete: no such file or directory: " + fileName);

        if (!f.canWrite())
            throw new IllegalArgumentException("Delete: write protected: "
          + fileName);

        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
            String[] files = f.list();
        if (files.length > 0)
            throw new IllegalArgumentException(
            "Delete: directory not empty: " + fileName);
        }

        // Attempt to delete it
        boolean success = f.delete();

        if (!success)
            throw new IllegalArgumentException("Delete: deletion failed");

    }

    public static void movefile(String srFile, String dtFile){
        // File (or directory) to be moved
        File file1 = new File(srFile);

        // Destination directory
        File file2 = new File(dtFile);

        // Move file to new directory
        boolean success = file1.renameTo(file2);
        if (!success) {
           // File was not successfully moved
          // throw new IllegalArgumentException(
          //"File was not successfully moved");
        }
 }

public static String Encrypt(String input) {
    String output = "";
    try {
        output = byteArrayToHexString(computeHash(input));
    }
    catch (Exception e){
        e.printStackTrace();
    }
    return output;
}

public static byte[] computeHash(String x)
  throws Exception
  {
     java.security.MessageDigest d =null;
     d = java.security.MessageDigest.getInstance("SHA-1");
     d.reset();
     d.update(x.getBytes());
     return  d.digest();
  }

  public static String byteArrayToHexString(byte[] b){
     StringBuffer sb = new StringBuffer(b.length * 2);
     for (int i = 0; i < b.length; i++){
       int v = b[i] & 0xff;
       if (v < 16) {
         sb.append('0');
       }
       sb.append(Integer.toHexString(v));
     }
     return sb.toString().toUpperCase();
  }
/**
 * Encrypt the bytes using given password.
 * @param input the bytes that are intended to be encrypted
 * @param pwd the password
 * @return
 */
public static byte[] cryptEncrypt(byte[] input, String pwd) {
	javax.crypto.Cipher cipher = cryptGetCipher(pwd, false);
	byte[] result = null;
	try {
		result = cipher.doFinal(input);
	} catch (javax.crypto.IllegalBlockSizeException ex) {
//		Ts.printErr(ex);
	} catch (javax.crypto.BadPaddingException ex) {
//		Ts.printErr(ex);
	}
	return result;
}

/**
 * Decrypt the bytes using given password.
 * @param cipherText bytes that are intended to be decrypted
 * @param pwd the password
 * @return
 */
public static byte[] cryptDecrypt(byte[] cipherText, String pwd) {
	javax.crypto.Cipher cipher = cryptGetCipher(pwd, true);
	byte[] result = null;
	try {
		result = cipher.doFinal(cipherText);
	} catch (javax.crypto.IllegalBlockSizeException ex) {
//		Ts.printErr(ex);
	} catch (javax.crypto.BadPaddingException ex) {
//		Ts.printErr(ex);
	}
	return result;
}
/**
 * Common part for encryption and decryption.
 * @param pwd the password
 * @param isDecryption <tt>true</tt> if this method is for decryption and <tt>false</tt> if this is for encryption
 * @return
 */
private static javax.crypto.Cipher cryptGetCipher(String pwd, boolean isDecryption) {
	//--- Get the hash algorithm
	java.security.MessageDigest md5 = null;
	try {
		md5 = java.security.MessageDigest.getInstance("MD5");
	} catch (java.security.NoSuchAlgorithmException ex) {
//		Ts.printErr(ex);
	}
	//--- Hash the pwd to make a 128bit key
	byte[] key = md5.digest(pwd.getBytes());
	//--- Create a key suitable for AES
	javax.crypto.spec.SecretKeySpec skey = new javax.crypto.spec.SecretKeySpec(key, "AES");
	javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(md5.digest(key));
	javax.crypto.Cipher cipher = null;
	try {
		cipher = javax.crypto.Cipher.getInstance("AES/CTR/NoPadding", "SunJCE");
	} catch (java.security.NoSuchAlgorithmException ex) {
//		Ts.printErr(ex);
	} catch (java.security.NoSuchProviderException ex) {
//		Ts.printErr(ex);
	} catch (javax.crypto.NoSuchPaddingException ex) {
//		Ts.printErr(ex);
	}
	try {
		if (isDecryption) {
			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skey, ivSpec);
		} else {
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, skey, ivSpec);
		}
	} catch (java.security.InvalidKeyException ex) {
//		Ts.printErr(ex);
	} catch (java.security.InvalidAlgorithmParameterException ex) {
//		Ts.printErr(ex);
	}
	return cipher;
    }

    public static byte[] toByteArray(char[] array) {
        return toByteArray(array, Charset.defaultCharset());
    }
    
    public static byte[] toByteArray(char[] array, Charset charset) {
        CharBuffer cbuf = CharBuffer.wrap(array);
        ByteBuffer bbuf = charset.encode(cbuf);
        return bbuf.array();
    }

    public static String GetData(String aData,int index, char delimeter) {
        // index start with 0 , Delimeter = delimeter
        aData = aData + delimeter;
        String ss = "";
        int ii = 0;
        int sindex = 0;
        int eindex = 0;
        while (ii<index) {
            // ,
            eindex = aData.indexOf(delimeter, sindex);
            if (eindex<0) {
                return "";
            }
            // Tab
            //eindex = aData.indexOf('\u0009', sindex);
            sindex = eindex + 1;
            ii++;
        }
        eindex = aData.indexOf(delimeter, sindex);
            if (eindex<0) {
                return "";
            }
        ss = aData.substring(sindex, eindex);
        return ss;
    }
  
    public static String GetCsv(String aData,int index, char delimeter) {
        // index start with 0 , Delimeter = delimeter
        aData = aData + delimeter;
        String ss = "";
        int ii = 0;
        int sindex = 0;
        int eindex = 0;
        
        char quot = '\u0022';
        while (ii<index) {
            // ,
            eindex = aData.indexOf(delimeter, sindex);
            if (eindex<0) {
                return "";
            }
            // Check " Quot
            if (aData.substring(sindex,sindex+1).equals(Character.toString(quot))) {
                sindex = sindex + 1;
                eindex = aData.indexOf(quot+",", sindex) + 1;
                if (eindex<0) {
                    return "";
                }
            }
            sindex = eindex + 1;
            ii++;
        }
        // Check " Quot
        if (aData.substring(sindex,sindex+1).equals(Character.toString(quot))) {
            sindex = sindex + 1;
            eindex = aData.indexOf(quot+",", sindex);
        } else {
            eindex = aData.indexOf(delimeter, sindex);
        }
        if (eindex<0) {
            return "";
        }
        ss = aData.substring(sindex, eindex);
        return ss;
    }
    
}
