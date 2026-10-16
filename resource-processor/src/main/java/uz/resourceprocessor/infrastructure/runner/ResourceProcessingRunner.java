package uz.resourceprocessor.infrastructure.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import uz.resourceprocessor.application.ResourceProcessingService;

import java.util.List;

@Component
public class ResourceProcessingRunner implements ApplicationRunner {

    private static final Logger log =
            LoggerFactory.getLogger(ResourceProcessingRunner.class);

    private final ResourceProcessingService processingService;

    public ResourceProcessingRunner(
            ResourceProcessingService processingService) {
        this.processingService = processingService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<String> values = args.getOptionValues("resource-id");

        if (values == null) {
            log.info("No resource ID supplied. Use --resource-id=123");
            return;
        }

        if (values.size() != 1) {
            throw new IllegalArgumentException(
                    "Provide exactly one --resource-id value"
            );
        }

        Long resourceId;

        try {
            resourceId = Long.valueOf(values.get(0));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "resource-id must be a valid integer",
                    exception
            );
        }

        Long songId = processingService.process(resourceId);

        log.info(
                "Processed resource {} successfully; created song {}",
                resourceId,
                songId
        );
    }
}