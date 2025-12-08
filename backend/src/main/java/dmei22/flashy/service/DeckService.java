package dmei22.flashy.service;

import dmei22.flashy.dto.deck.*;
import dmei22.flashy.model.Deck;
import dmei22.flashy.model.Image;
import dmei22.flashy.repository.DeckRepository;
import dmei22.flashy.service.mapper.DeckMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class DeckService {

    private final DeckRepository deckRepository;
    private final ImageService imageService;

    public DeckService(
            DeckRepository deckRepository,
            ImageService imageService
    ) {
        this.deckRepository = deckRepository;
        this.imageService = imageService;
    }

    // CREATE
    public DeckOverviewDto create(DeckCreateRequest request) {
        Deck deck = new Deck();

        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        return DeckMapper.toDeckOverviewDto(this.deckRepository.save(deck));
    }

    // TODO: CREATE IMAGE
    public Image uploadImage(Long deckId, MultipartFile file) {
        Image image = this.imageService.upload(file);
        Deck deck = this.deckRepository.findById(deckId).get();

        deck.setImage(image);

        this.deckRepository.save(deck);
        return image;
    }

    // READ
    public DeckDetailsDto findById(Long id) {
        Deck deck = this.deckRepository.findById(id).get();

        return DeckMapper.toDeckDetailsDto(deck);
    }

    public List<DeckOverviewDto> all() {
        List<DeckOverviewDto> decks = this.deckRepository.findAll()
                .stream()
                .map(DeckMapper::toDeckOverviewDto)
                .toList();

        return decks;
    }

    public Image deckImage(Long deckId) {
        Deck deck = this.deckRepository.findById(deckId).get();
        Image image = deck.getImage();

        return image;
    }

    // UPDATE
    public DeckDetailsDto update(DeckUpdateRequest request) {
        Deck deck = this.deckRepository.findById(request.getDeckId()).get();

        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        return DeckMapper.toDeckDetailsDto(this.deckRepository.save(deck));
    }

    public void updateImage(Long deckId, MultipartFile file) {
        Deck deck = this.deckRepository.findById(deckId).get();
        Image image = this.imageService.update(deck.getImage().getId(), file);

        deck.setImage(image);

        this.deckRepository.save(deck);
    }

    // DELETE
    public void deleteById(Long id) {
        this.deckRepository.deleteById(id);
    }

    public void deleteImage(Long deckId) {
        Deck deck = this.deckRepository.findById(deckId).get();

        deck.setImage(null);

        this.deckRepository.save(deck);
    }
}
