package mr.gov.masef.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Demande;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
@Service
public class DemandeSseService {


    private final List<FluxSink<Demande>> clients = new ArrayList<>();

    // Ajout d'un nouvel enregistrement et notification des clients
    public void notifyChange(Demande demande) {
        for (FluxSink<Demande> client : clients) {
            client.next(demande); // Envoyer la demande modifiée à tous les clients
        }
    }

    // Méthode pour retourner un flux d'événements SSE aux clients
    public Flux<Demande> getSseFlux() {
        return Flux.create((Consumer<FluxSink<Demande>>) clients::add, FluxSink.OverflowStrategy.LATEST);
    }
}
