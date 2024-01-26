package it.almaviva.difesa.security.user.dto;


import it.almaviva.difesa.shared.common.dto.GenericResponseDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TbAndipDipendenteDTO implements GenericResponseDTO {

    private Long andipIdPk;
    private String andipCogn;
    private String andipNome;
    private LocalDate andipDataNascita;
    private String andipCodMatricola;
    private String andipCodFsc;
    private LocalDate andipDataDecesso;
    private Character andipFlagSesso;
    private String andipMailUfficio;
    private String andipIndDom;
    private String andipCapDom;
    private String andipIndRes;
    private String andipCapRes;
    private String andipTelRes;
    private String andipNoteAnagr;
    private LocalDate maritalStatusDate;
    private Long andipStcomIdNas;
    private Long andipStcomIdRes;
    private Long andipStcomIdDom;
    private Long andipStnazIdNas;
    private Long andipAntprId;
    private Long andipAnstcId;
    private String andipCodCarta;
    private Long andipStnazIdCit;
    private String andipStfaaId;
    private String andipLocRes;
    private String andipLocDom;
    private Long andipStnazIdRes;
    private Long andipStnazIdDom;
    private LocalDateTime andipDataIns;
    private LocalDateTime andipDataUltAgg;
    private String andipCodUltAgg;
    private Integer andipFlagAid;
    private String andipAnstrCod;
    private String andipAnstsCod;
    private Integer andipFlagBdus;
}
