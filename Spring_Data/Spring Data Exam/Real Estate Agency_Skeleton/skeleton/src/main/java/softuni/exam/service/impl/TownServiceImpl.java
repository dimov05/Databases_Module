package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownImportDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {
    public static final String TOWNS_PATH = "C:\\Users\\PC\\Desktop\\Databases_Module\\Spring_Data\\Spring Data Exam\\Real Estate Agency_Skeleton\\skeleton\\src\\main\\resources\\files\\json\\towns.json";
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public TownServiceImpl(ModelMapper modelMapper, TownRepository townRepository, Gson gson, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TOWNS_PATH)));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        TownImportDto[] townImportDtos = this.gson.fromJson(this.readTownsFileContent(), TownImportDto[].class);

        for (TownImportDto townImportDto : townImportDtos) {
            if (this.validatorUtil.isValid(townImportDto) && this.townRepository.findByTownName(townImportDto.getTownName()) == null) {
                this.townRepository.saveAndFlush(this.modelMapper.map(townImportDto,Town.class));
                sb.append(String.format("Successfully imported town %s - %d",
                                townImportDto.getTownName(), townImportDto.getPopulation()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid town").append(System.lineSeparator());
            }
        }


        return sb.toString();
    }
}
