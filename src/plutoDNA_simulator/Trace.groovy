package plutoDNA_simulator
import java.util.Date


class Trace {


	def static final LOG_LEVEL = "info"
		
	def static info(className, string, threadID = ""){
		if(LOG_LEVEL =~ /info/){
			string = threadID +"INFO: " + string
			log(className, string)
		}
	}
	
	def static warning(className, string, threadID = ""){
		
		if(LOG_LEVEL =~ /warning/){
			string = threadID +"WARNING: " + string 
			log(className, string)
		}
	}
	
	
	def static error(className, string, threadID = ""){
		
		if(LOG_LEVEL =~ /error/){
			string = threadID +"ERROR: " + string
			log(className, string)
		}
	}
	
	def static log(className, string){
		
		def timeStamp = Date.getDateTimeString()
		def f = new File("className" + timeStamp).withWriter { out ->
			out.writeLine(string)
		}
		
		
	}

	
	
	
}
