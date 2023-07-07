package com.kakaoent.md.domain.code

import com.fasterxml.jackson.databind.BeanDescription
import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.domain.EXCLUDE_DELETION
import jakarta.persistence.*
import org.hibernate.annotations.Where

@Entity
@Table(name = "code_category")
class CodeCategory(

    name: String,

    description: String,

    codes: List<Code> = mutableListOf()
) : AuditingEntity() {

    @Column(name = "name", nullable = false, length = 30)
    var name: String = name
        protected set

    @Column(name = "description", nullable = false, length = 100)
    var description: String = description
        protected set

    @OneToMany(mappedBy = "codeCategory", cascade = [CascadeType.ALL])
    @Where(clause = EXCLUDE_DELETION)
    protected val _codes: MutableList<Code> = codes.toMutableList()
    val codes: List<Code> get() = _codes.toList()

    fun addCode(code: Code) {
        _codes.add(code)
    }

    fun removeCode(code: Code) {
        _codes.remove(code)
        code.delete()
    }

    override fun delete() {
        super.delete()
        codes.onEach(Code::delete)
    }

    fun update(name: String, description: String) {
        this.name = name
        this.description = description
    }

    fun updateCode(codeName: String, codeDescription: String) {
        codes.find { it.name == codeName }
            ?.update(
                name = codeName,
                description = codeDescription
            )
    }

    fun deleteCode(codeName: String) {
        codes.find { it.name == codeName }
            ?.delete()
    }
}

@Entity
@Table(name = "code")
class Code(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    val codeCategory: CodeCategory,

    name: String,

    description: String,
) : AuditingEntity() {

    @Column(name = "name", nullable = false, length = 50)
    var name: String = name
        protected set

    @Column(name = "description", nullable = false, length = 255)
    var description: String = description
        protected set

    fun update(name: String, description: String) {
        this.name = name
        this.description = description
    }
}