package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.xmls.SellerImportDto;
import softuni.exam.models.dtos.xmls.SellerRootImportDto;
import softuni.exam.models.entities.Rating;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final static String SELLER_PATH = "src/main/resources/files/xml/sellers.xml";
    private final SellerRepository sellerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(SELLER_PATH)));
    }

    @Override
    public String importSellers() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        SellerRootImportDto sellerRootImportDto = this.xmlParser.parseXml(SellerRootImportDto.class, SELLER_PATH);
        for (SellerImportDto sellerImportDto : sellerRootImportDto.getSellers()) {
            Optional<Seller> byEmail = this.sellerRepository.findByEmail(sellerImportDto.getEmail());
            Rating rating;
            try {
                rating = Rating.valueOf(sellerImportDto.getRating());
            } catch (Exception e) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }
            if (this.validationUtil.isValid(sellerImportDto) && byEmail.isEmpty()) {
                Seller seller = this.modelMapper.map(sellerImportDto, Seller.class);
                seller.setRating(rating);

                this.sellerRepository.saveAndFlush(seller);

                sb.append(String.format("Successfully import seller %s - %s",
                                seller.getLastName(), seller.getEmail()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid seller").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
