/*
 * Copyright (C) 2011-2013 spray.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spray.http
package parser

import org.parboiled.scala._
import BasicRules._

private[parser] trait AcceptRangesHeader {
  this: Parser with ProtocolParameterRules ⇒

  def `*Accept-Ranges` = rule(
    RangeUnitsDef ~ EOI ~~> (HttpHeaders.`Accept-Ranges`(_)))

  def RangeUnitsDef = rule {
    NoRangeUnitsDef | zeroOrMore(RangeUnit, separator = ListSep)
  }

  def NoRangeUnitsDef = rule {
    "none" ~ push(List.empty[RangeUnit])
  }

}