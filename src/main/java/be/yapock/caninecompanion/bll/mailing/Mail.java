package be.yapock.caninecompanion.bll.mailing;

import lombok.*;

import java.util.Map;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Mail {
    @Getter
    public static class MailTemplate{
        private String template;
        private Map<String, Object> props;

        public MailTemplate(String template, Map<String, Object> props){
            this.template = template;
            this.props = props;
        }
    }
    private String from;
    private String to;
    private MailTemplate template;
    private String subject;
}
