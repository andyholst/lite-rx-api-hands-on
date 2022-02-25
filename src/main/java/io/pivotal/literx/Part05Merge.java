package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 */
public class Part05Merge {

//========================================================================================

	Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
		return Flux.merge(flux1, flux2);
	}

//========================================================================================

	Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
		return Flux.mergeSequential(flux1, flux2);
	}

//========================================================================================

	Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
		return Flux.mergeSequential(mono1, mono2);
	}

}
