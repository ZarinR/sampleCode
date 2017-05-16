
package com.mojonetworks.apiClient.accessor.common;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * TODO: Check the use of this class, remove this if not required for any other purpose
 */

public class WebAPIUtility {

//	public static void downloadURLResource(APISession session, String url, OutputStream outputStream) throws ProcessingException {
//		InputStream inputStream = null;
//		try {
//			SSLContext sc = SSLContext.getInstance(APISession.PROTOCOL_SSL);
//			sc.init(null, new TrustManager[]{new APISession.SGTrustManager()}, new java.security.SecureRandom());
//			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//	
//			// Create all-trusting host name verifier
//			HostnameVerifier allHostsValid = new HostnameVerifier() {
//				public boolean verify(String hostname, SSLSession session) {
//					return true;
//				}
//			};
//	
//			// Install the all-trusting host verifier
//			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//	
//			URL fileUrl = new URL(session.getTarget().getUri().toString().replace("webservice", "") + url);
//			HttpsURLConnection conn = (HttpsURLConnection)fileUrl.openConnection();
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.setUseCaches(false);
//			conn.setRequestProperty("Cookie", APISession.JSESSION_ID+"="+session.getJsessionid());
//	
//			inputStream = conn.getInputStream();
//			int readBytes;
//	
//			byte[] src_bytes=new byte[1024];
//			while((readBytes=inputStream.read(src_bytes,0,1024))!=-1){
//				outputStream.write(src_bytes,0,readBytes);
//			}
//	
//		} catch (NoSuchAlgorithmException e) {
//			//			TODO: different exception for visibility data
//			throw new ProcessingException(ProcessingException.ERR_CODE_ANALYTICS_DATA_GENERATION, ProcessingException.MSG_ERROR_ANALYTICS_DATA_GENERATION);
//		} catch (KeyManagementException e) {
//			throw new ProcessingException(ProcessingException.ERR_CODE_ANALYTICS_DATA_GENERATION, ProcessingException.MSG_ERROR_ANALYTICS_DATA_GENERATION);
//		}  catch (IOException e1) {
//			throw new ProcessingException(ProcessingException.ERR_CODE_ANALYTICS_DATA_GENERATION, ProcessingException.MSG_ERROR_ANALYTICS_DATA_GENERATION);
//		} finally{
//			WebAPIUtility.closeStream(outputStream);
//			WebAPIUtility.closeStream(inputStream);
//		}
//	}

	public static <T> String convertDOToJSON(T dataObject) throws APIClientException {
		try {
			return new ObjectMapper().writeValueAsString(dataObject);
		} catch (JsonGenerationException e) {
			throw new APIClientException("Invalid data object",e);
		} catch (JsonMappingException e) {
			throw new APIClientException("Invalid data object",e);
		} catch (IOException e) {
			throw new APIClientException(e);
		}
	}

//	public static LocationId getFirstAllowedLocationId(User user){
//		/**
//		 * Get first allowed location
//		 */
//		Collection<LocationId> allowedLocations = user.getAllowedLocations();
//		return allowedLocations.iterator().next();
//	}
//	public static void checkContentModified(final Date lastModifiedDate,Request request, HttpServletResponse response) {
//		final String ifModifiedSince = request.getHeader(HttpHeaders.IF_MODIFIED_SINCE);
//		if(ifModifiedSince!=null && !ifModifiedSince.isEmpty()) {
//			final Date ifModifiedSinceDate = org.apache.http.client.utils.DateUtils.parseDate(ifModifiedSince);
//			if(ifModifiedSinceDate == null){//If header contains invalid date DateUtils.parseDate() returns null value
//				throw new APIClientException("Invalid ''If-Modified-Since'' request header value.");	
//			}
//			
//			if(!lastModifiedDate.after(ifModifiedSinceDate)) {
//				//No need to send the resource since it has not been modified.
//				throw new APIClientException("Content is not modified.");
//			}
//		}
//		final String lastModifiedHTMLDate = DateUtils.formatDate(lastModifiedDate);
//		response.addHeader(HttpHeaders.LAST_MODIFIED, lastModifiedHTMLDate);
//	}

	/**
	 * Converts milliSeconds to Date
	 * @param milliSeconds
	 * @return
	 */
//	public static Date getDateFromMilliSeconds(final long milliSeconds) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTimeInMillis((milliSeconds/1000)*1000);
//		final Date lastModifiedDate = cal.getTime();
//		return lastModifiedDate;
//	}
}
