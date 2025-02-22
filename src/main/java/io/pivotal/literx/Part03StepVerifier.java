/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

//========================================================================================

	void expectFooBarComplete(Flux<String> flux) {
		StepVerifier
				.create(flux)
				.expectNext("foo")
				.expectNext("bar")
				.verifyComplete();
	}

//========================================================================================

	void expectFooBarError(Flux<String> flux) {
		StepVerifier
				.create(flux)
				.expectNext("foo")
				.expectNext("bar")
				.verifyError(RuntimeException.class);
	}

//========================================================================================

	void expectSkylerJesseComplete(Flux<User> flux) {
		StepVerifier
				.create(flux)
				.expectNextMatches(user -> "swhite".equals(user.getUsername()))
				.expectNextMatches(user -> "jpinkman".equals(user.getUsername()))
				.verifyComplete();
	}

//========================================================================================

	void expect10Elements(Flux<Long> flux) {
		StepVerifier
				.create(flux)
				.thenAwait(Duration.ofSeconds(1L))
				.expectNext(0L)
				.expectNext(1L)
				.expectNext(2L)
				.expectNext(3L)
				.expectNext(4L)
				.expectNext(5L)
				.expectNext(6L)
				.expectNext(7L)
				.expectNext(8L)
				.expectNext(9L)
				.verifyComplete();
	}

//========================================================================================

	void expect3600Elements(Supplier<Flux<Long>> supplier) {
		StepVerifier.withVirtualTime(supplier)
				.thenAwait(Duration.ofSeconds(3600))
				.expectNextCount(3600)
				.verifyComplete();
	}
}
