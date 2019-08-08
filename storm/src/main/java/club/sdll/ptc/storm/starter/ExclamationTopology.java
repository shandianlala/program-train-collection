package club.sdll.ptc.storm.starter;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 * description
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2018年10月16 23:21:38
 */
public class ExclamationTopology {
	
	public static class ExclamationBolt extends BaseRichBolt {
		private static final long serialVersionUID = 8810614625797353333L;
		OutputCollector collector;

		@Override
		public void execute(Tuple tuple) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
	

}
