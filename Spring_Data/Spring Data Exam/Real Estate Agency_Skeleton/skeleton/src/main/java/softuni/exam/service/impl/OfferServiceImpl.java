package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferImportDto;
import softuni.exam.models.dto.OfferRootImportDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    public static final String OFFERS_PATH = "C:\\Users\\PC\\Desktop\\Databases_Module\\Spring_Data\\Spring Data Exam\\Real Estate Agency_Skeleton\\skeleton\\src\\main\\resources\\files\\xml\\offers.xml";
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ApartmentRepository apartmentRepository;
    private final ValidatorUtil validatorUtil;
    private final OfferRepository offerRepository;
    private final AgentService agentService;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, ApartmentRepository apartmentRepository, ValidatorUtil validatorUtil, OfferRepository offerRepository, AgentService agentService) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.apartmentRepository = apartmentRepository;
        this.validatorUtil = validatorUtil;
        this.offerRepository = offerRepository;

        this.agentService = agentService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(OFFERS_PATH)));
    }

    @Override
    public String importOffers() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        OfferRootImportDto offerRootImportDto = this.xmlParser.parseXml(OfferRootImportDto.class, OFFERS_PATH);

        for (OfferImportDto offerImportDto : offerRootImportDto.getOffers()) {
            if (validatorUtil.isValid(offerImportDto)) {
                Offer offer = this.modelMapper.map(offerImportDto, Offer.class);
                offer.setApartment(this.apartmentRepository.getById(offerImportDto.getApartmentId().getId()));
                Agent agent = this.agentService.getAgentByName(offerImportDto.getName().getName());
                offer.setAgent(agent);
                this.offerRepository.saveAndFlush(offer);

                sb.append(String.format("Successfully imported offer %s",
                                offerImportDto.getPrice()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid offer").append(System.lineSeparator());
            }
        }


        return sb.toString();
    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();
        List<Offer> offersWithThreeRoomsApartmentsSorted = this.offerRepository.findAllByApartmentByApartmentTypeThreeRoomsOrderByApartmentAreaDescPriceAsc();

        offersWithThreeRoomsApartmentsSorted
                .forEach(offer -> sb.append(String.format("Agent %s %s with offer №%d:%n" +
                                        "   -Apartment area: %.2f%n" +
                                        "   --Town: %s%n" +
                                        "   ---Price: %.2f$",
                                offer.getAgent().getFirstName(), offer.getAgent().getLastName(), offer.getId(),
                                offer.getApartment().getArea(),
                                offer.getApartment().getTown().getTownName(),
                                offer.getPrice()))
                        .append(System.lineSeparator()));

        return sb.toString();
    }
}
