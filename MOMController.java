package windpark;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

/**
 * Controller class
 */
@RestController
public class MOMController {
    private StringBuilder messageQueueResultsBuilder = new StringBuilder();

    @CrossOrigin
    @RequestMapping(value = "/warehouse/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String allWarehouseData()    {
        // send & read & return all messages from the queue
        new warehouse.MOMSender();
        formatJSONString(new MOMReceiver().getAllWarehouseData());
        return this.messageQueueResultsBuilder.toString();
    }


    /**
     * formats a java string into a valid JSON string
     * @param newMessage
     */
    public void formatJSONString(String newMessage)    {
        if (this.messageQueueResultsBuilder.isEmpty()) {
            this.messageQueueResultsBuilder.append("[").append(newMessage).append("]");
        } else if (this.messageQueueResultsBuilder.charAt(this.messageQueueResultsBuilder.length() - 1) == ']') {
            this.messageQueueResultsBuilder.deleteCharAt(this.messageQueueResultsBuilder.length() - 1);
            this.messageQueueResultsBuilder.append(",").append(newMessage).append("]");
        }
    }
}