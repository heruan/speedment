/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.field.internal.predicate.reference;

import com.speedment.runtime.field.internal.predicate.AbstractFieldPredicate;
import com.speedment.runtime.field.predicate.FieldPredicate;
import com.speedment.runtime.field.trait.HasReferenceValue;

import static com.speedment.runtime.field.predicate.PredicateType.IS_NOT_NULL;

/**
 *
 * @param <ENTITY>  the entity type
 * @param <D>       the database type
 * @param <V>       the value type
 * 
 * @author  Per Minborg
 * @since   2.2.0
 */
public final class ReferenceIsNotNullPredicate<ENTITY, D, V>
        extends AbstractFieldPredicate<ENTITY, V, HasReferenceValue<ENTITY, D, V>> {
    
    public ReferenceIsNotNullPredicate(HasReferenceValue<ENTITY, D, V> field) {
        this(field, false);
    }
    
    ReferenceIsNotNullPredicate(HasReferenceValue<ENTITY, D, V> field, boolean negated) {
        super(IS_NOT_NULL, field, entity -> entity != null && field.get(entity) != null, negated);
    }

    @Override
    public ReferenceIsNotNullPredicate<ENTITY, D, V> negate() {
        return new ReferenceIsNotNullPredicate<>(getField(), !isNegated());
    }
    
}