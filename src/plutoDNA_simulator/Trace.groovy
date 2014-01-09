package plutoDNA_simulator
import java.util.Date


class Trace {

	
	def static LEVEL = LOG_LEVEL.ERROR
	
	static enum LOG_LEVEL {
		ERROR, WARNING, INFO
	}
	
	def static setLogLevel(level){
		
		
		
		
		if(!(level instanceof LOG_LEVEL) && !(level instanceof Integer)){
			throw new Exception()
		}
		
		if (level==LOG_LEVEL.ERROR || (int)level == LOG_LEVEL.ERROR.value()){
			LOG_LEVEL.ERROR
		}
		else if (level==LOG_LEVEL.WARNING || (int)level == LOG_LEVEL.WARNING.value()){
			LOG_LEVEL.WARNING
		}
		else if (level==LOG_LEVEL.INFO || (int)level == LOG_LEVEL.INFO.value()){
			LOG_LEVEL.INFO
		}
		else{
			throw new Exception()
		}
		
		
	}
		
	def static info(className = "trace", string, threadID = ""){
		if (LEVEL >= LOG_LEVEL.INFO){
			string = threadID +"INFO: " + string
			log(className, string)
		}
	}
	
	def static warning(className = "trace", string, threadID = ""){
		
		if(LEVEL <= LOG_LEVEL.WARNING){
			string = threadID +"WARNING: " + string 
			log(className, string)
		}
	}
	
	
	def static error(className = "trace", string, threadID = ""){
		
		
		if(LEVEL <= LOG_LEVEL.ERROR){
			string = threadID +"ERROR: " + string
			log(className, string)
		}
	}
	
	def static log(className, threadID = "", string){
		
		//def date = new Date()
		//String timeStamp = date.getDateTimeString()
		def f = new File(className + ".log").withWriter { out ->
			out.writeLine(string)
		}
		
		
	}

	
	
	
}
