package it.almaviva.difesa.security.service;


import it.almaviva.difesa.security.user.dto.TbAndipDipendenteDTO;
import it.almaviva.difesa.security.user.dto.VwSg155StgiuridicoDTO;
import java.net.URISyntaxException;


public interface MSSipadService {

    VwSg155StgiuridicoDTO findUserByFiscalCode(String fiscalCode) throws URISyntaxException;
   TbAndipDipendenteDTO findUserByCode(String fiscalCode) throws URISyntaxException;
}
