package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    public static final String AGENTS_PATH = "C:\\Users\\PC\\Desktop\\Databases_Module\\Spring_Data\\Spring Data Exam\\Real Estate Agency_Skeleton\\skeleton\\src\\main\\resources\\files\\json\\agents.json";
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
@Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(AGENTS_PATH)));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder sb = new StringBuilder();
        AgentImportDto[] agentImportDtos = this.gson.fromJson(this.readAgentsFromFile(), AgentImportDto[].class);

        for (AgentImportDto agentImportDto : agentImportDtos) {
            Optional<Agent> byFirstName = this.agentRepository.findByFirstName(agentImportDto.getFirstName());

            if (validatorUtil.isValid(agentImportDto) && byFirstName.isEmpty()) {
                Agent agent = this.modelMapper.map(agentImportDto, Agent.class);
                agent.setTown(this.townRepository.findByTownName(agentImportDto.getTownName()));
                this.agentRepository.save(agent);

                sb.append(String.format("Successfully imported agent - %s %s",
                                agent.getFirstName(), agent.getLastName()))
                        .append(System.lineSeparator());

            } else {
                sb.append("Invalid agent").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public Agent getAgentByName(String name) {
        return this.agentRepository.findByFirstName(name).orElse(null);
    }
}
