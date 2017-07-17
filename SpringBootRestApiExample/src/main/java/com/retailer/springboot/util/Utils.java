package com.retailer.springboot.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.springboot.model.Shop;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
@Component
public class Utils
    {

	/** The mapper. */
	ObjectMapper mapper = new ObjectMapper();

	// static Logger log = Logger.getLogger(Utils.class.getName());

	// Incoming parameter JSON DATA
	/**
	 * Object mapper json to java.
	 *
	 * @param data the data
	 * @param classObject the class object
	 * @return the object
	 */
	// Outgoing Java Object
	public Object objectMapperJsonToJava(String data, Class classObject)
	    {
		try
		    {
			Object object = mapper.readValue(data, classObject.newInstance().getClass());
			return object;
		    }
		catch (Exception e)
		    {
			// log.error(ExceptionConstants.E004.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	// Incoming parameter Java Object
	/**
	 * Object mapper java to json.
	 *
	 * @param classObject the class object
	 * @return the string
	 */
	// Outgoing Json String
	public String objectMapperJavaToJson(Object classObject)
	    {
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, classObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E005.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	// Incoming parameter Java List
	/**
	 * Object mapper list to json.
	 *
	 * @param listObject the list object
	 * @return the string
	 */
	// Outgoing Json String
	public String objectMapperListToJson(List<?> listObject)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("total", listObject.size());
		mapObject.put("data", listObject);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Convert leg desc to list.
	 *
	 * @param listObject the list object
	 * @param rideLeadId the ride lead id
	 * @return the string
	 */
	public String convertLegDescToList(List<?> listObject, String rideLeadId)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("total", listObject.size());
		mapObject.put("data", listObject);
		mapObject.put("rideLeadId", rideLeadId);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper list to json.
	 *
	 * @param listObject the list object
	 * @param totalCount the total count
	 * @return the string
	 */
	public String objectMapperListToJson(List<?> listObject, long totalCount)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("total", totalCount);
		mapObject.put("data", listObject);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	// No Incoming parameter
	/**
	 * Object mapper success.
	 *
	 * @return the string
	 */
	// Outgoing Json String
	public String objectMapperSuccess()
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E007.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	// No Incoming parameter
	/**
	 * Object mapper error.
	 *
	 * @param msg the msg
	 * @return the string
	 */
	// Outgoing Json String
	public String objectMapperError(String msg)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", false);
		mapObject.put("message", msg);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E007.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper string to json.
	 *
	 * @param string the string
	 * @return the string
	 */
	public String objectMapperStringToJson(String string)
	    {
		// TODO Auto-generated method stub
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("data", string);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E007.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper multiple list to json.
	 *
	 * @param stageCodesList the stage codes list
	 * @param transitionCodesList the transition codes list
	 * @return the string
	 */
	public String objectMapperMultipleListToJson(List<HashMap<Object, Object>> stageCodesList,
		List<HashMap<Object, Object>> transitionCodesList)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("total", stageCodesList.size() + transitionCodesList.size());
		mapObject.put("stageCodesList", stageCodesList);
		mapObject.put("transitionCodesList", transitionCodesList);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper two list to json.
	 *
	 * @param stageCodesList the stage codes list
	 * @param transitionCodesList the transition codes list
	 * @return the string
	 */
	public String objectMapperTwoListToJson(List stageCodesList, List transitionCodesList)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);

		if (transitionCodesList != null)
		    {
			mapObject.put("total", stageCodesList.size() + transitionCodesList.size());
			mapObject.put("stageCodesList", stageCodesList);
			mapObject.put("transitionCodesList", transitionCodesList);
		    }
		else
		    {
			mapObject.put("total", stageCodesList.size());
			mapObject.put("stageCodesList", stageCodesList);
		    }

		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper multiple list to json.
	 *
	 * @param totalNumberOfStageList the total number of stage list
	 * @param pendingNumberOfStageList the pending number of stage list
	 * @param pendingNumberOfTransitionList the pending number of transition list
	 * @return the string
	 */
	public String objectMapperMultipleListToJson(List<HashMap<Object, Object>> totalNumberOfStageList,
		List<HashMap<Object, Object>> pendingNumberOfStageList,
		List<HashMap<Object, Object>> pendingNumberOfTransitionList)
	    {
		// TODO Auto-generated method stub
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("total", totalNumberOfStageList.size() + pendingNumberOfStageList.size()
			+ pendingNumberOfTransitionList.size());
		mapObject.put("totalStageCount", totalNumberOfStageList);
		mapObject.put("pendingStageCount", pendingNumberOfStageList);
		mapObject.put("totalTransitionCount", pendingNumberOfTransitionList);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper success.
	 *
	 * @param string the string
	 * @return the string
	 */
	public String objectMapperSuccess(String string)
	    {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		mapObject.put("message", string);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E007.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	/**
	 * Object mapper success.
	 *
	 * @param object the object
	 * @param msg the msg
	 * @return the string
	 */
	public String objectMapperSuccess(Object object, String msg)
	    {
		try
		    {
			Map<String, Object> mapObject = new HashMap<String, Object>();
			ObjectMapper mapperJackson = new ObjectMapper();
			mapObject.put("success", true);
			mapObject.put("data", object);
			mapObject.put("message", msg);
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E007.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	    }

	public String objectMapperIterableToJson(Iterable<?> listObject) {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		mapObject.put("success", true);
		//mapObject.put("total", listObject);
		mapObject.put("data", listObject);
		try
		    {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		    }
		catch (Exception e)
		    {
			// TODO: handle exception
			// log.error(ExceptionConstants.E006.getExceptionMessage(),
			// e);
			e.printStackTrace();
		    }
		return null;
	}

	
	
	/*public String mapToJson(Map<String, Object> map){
	    
	    try {
		
		            mapper.writeValue(new File(jsonFilePath), mapObject);
		
		 
		
		        } catch (JsonGenerationException e) {
		
		            e.printStackTrace();
		
		        } catch (JsonMappingException e) {
		
		            e.printStackTrace();
		
		        } catch (IOException e) {
		
		            e.printStackTrace();
		
		        }

	    return null;
	    
	}
*/
    }
