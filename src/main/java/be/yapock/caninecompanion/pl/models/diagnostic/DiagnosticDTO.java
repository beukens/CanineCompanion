package be.yapock.caninecompanion.pl.models.diagnostic;

import be.yapock.caninecompanion.dal.models.Diagnostic;
import be.yapock.caninecompanion.pl.models.dog.DogShortDTO;

import java.time.LocalDate;

public record DiagnosticDTO(
        Long id,
        LocalDate date,
        int submissivePosition,
        int withFamiliarHuman,
        int withStranger,
        int withDogs,
        int withOtherAnimals,
        int stayingAlone,
        int affrayed,
        int contactWHumans,
        int contactWAnimals,
        int adaptability,
        int attachement,
        int separation,
        int restPlace,
        int exploration,
        int tenderness,
        int vocalize,
        int jumpOnPeople,
        int destruct,
        int scratchesBruises,
        int excitation,
        DogShortDTO dog) {
    public static DiagnosticDTO fromEntity(Diagnostic diagnostic){
        return new DiagnosticDTO(diagnostic.getId(), diagnostic.getDate(), diagnostic.getSubmissivePosition(), diagnostic.getWithFamiliarHuman(), diagnostic.getWithStranger(), diagnostic.getWithDogs(), diagnostic.getWithOtherAnimals(), diagnostic.getStayingAlone(), diagnostic.getAffrayed(), diagnostic.getContactWHumans(), diagnostic.getContactWAnimals(), diagnostic.getAdaptability(), diagnostic.getAttachement(), diagnostic.getSeparation(), diagnostic.getRestPlace(), diagnostic.getExploration(), diagnostic.getTenderness(), diagnostic.getVocalize(), diagnostic.getJumpOnPeople(), diagnostic.getDestruct(), diagnostic.getScratchesBruises(), diagnostic.getExcitation(), DogShortDTO.fromEntity(diagnostic.getDog()));
    }
}
