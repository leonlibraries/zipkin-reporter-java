/**
 * Copyright 2016 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package zipkin.reporter;

import zipkin.Codec;
import zipkin.Span;

/**
 * @param <S> type of the span, usually {@link Span}
 */
public interface Encoder<S> {
  Encoder<Span> JSON = new Encoder<Span>() {
    @Override public Encoding encoding() {
      return Encoding.JSON;
    }

    @Override public byte[] encode(Span span) {
      return Codec.JSON.writeSpan(span);
    }
  };
  Encoder<Span> THRIFT = new Encoder<Span>() {
    @Override public Encoding encoding() {
      return Encoding.THRIFT;
    }

    @Override public byte[] encode(Span span) {
      return Codec.THRIFT.writeSpan(span);
    }
  };

  Encoding encoding();

  /**
   * Serialize a span recorded from instrumentation into its binary form.
   *
   * @param span cannot be null
   */
  byte[] encode(S span);
}
