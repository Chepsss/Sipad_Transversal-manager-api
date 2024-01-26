package it.almaviva.difesa.security.service.impl;


import it.almaviva.difesa.security.service.MSSipadService;
import it.almaviva.difesa.security.user.dto.TbAndipDipendenteDTO;
import it.almaviva.difesa.security.user.dto.VwSg155StgiuridicoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@Slf4j
public class MSSipadServiceImpl implements MSSipadService {

    @Value("${ms.sipad.base.url}")
    private String msSipadBaseUrl;

    @Value("${ms.sipad.user-fiscalcode}")
    private String filterUsers;

    @Value("${ms.sipad.employee-fiscalcode}")
    private String getEmployeeByFiscalCode;

    private final WebClient client = WebClient.create(msSipadBaseUrl);


    @Override
    public VwSg155StgiuridicoDTO findUserByFiscalCode(String fiscalCode) throws URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("fiscalCode", fiscalCode);
        var uri = createUri(msSipadBaseUrl, filterUsers, params);
        return client.get().uri(uri).retrieve()
        .bodyToMono(VwSg155StgiuridicoDTO.class).block();
    }


    @Override
    public TbAndipDipendenteDTO findUserByCode(String fiscalCode) throws URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("fiscalCode", fiscalCode);
        var uri = createUri(msSipadBaseUrl, getEmployeeByFiscalCode, params);
        return client.get().uri(uri).retrieve()
                .bodyToMono(TbAndipDipendenteDTO.class).block();
    }



    /***
     * Method to create the uri for webclient, it takes base url, path and query params.
     * It constructs the uri in base of the presence of query params. It they
     * @param baseUrl
     * @param path
     * @param queryParams
     * @return
     * @throws URISyntaxException
     */
    public URI createUri (String baseUrl, String path, Map<String, String> queryParams) throws URISyntaxException {
        String protocol = "";
        if (queryParams==null) {
            protocol = "http://";
            return new URI(protocol + baseUrl + path);
        }
        else {
            var mapParams = queryParams.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getKey,
                    Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
            return UriComponentsBuilder.fromPath(protocol + baseUrl + path).queryParams(new LinkedMultiValueMap<>(mapParams)).build().encode().toUri();
        }
    }

    /***
     * Overloaded signature for the method to build the uri for webclient. This is for
     * uri without params
     * @param baseUrl base of the url
     * @param path path for specific service
     * @return a URI
     * @throws URISyntaxException
     */
    public URI createUri (String baseUrl, String path) throws URISyntaxException {
        return createUri (baseUrl, path, null);
    }

}
