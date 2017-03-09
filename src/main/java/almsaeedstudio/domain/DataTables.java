package almsaeedstudio.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;


/**
 * Created by oleg on 3/9/17.
 */

@NamedQueries({
        @NamedQuery(name = "DataTables.findAll", query = "FROM DataTables"),
        @NamedQuery(name = "DataTables.fingById", query = "FROM DataTables d Where d.id =:id")
})

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@Table(name = "data_tables")
public class DataTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rendering_engine", columnDefinition = "VARCHAR(45)", nullable = false)
    @JsonProperty(value = "Rendering_engine")
    private String renderingEngine;

    @Column(name = "browser", columnDefinition = "VARCHAR(45)", nullable = false)
    @JsonProperty(value = "Browser")
    private String browser;

    @Column(name = "platform", columnDefinition = "VARCHAR(45)", nullable = false)
    @JsonProperty(value = "Platform")
    private String platform;

    @Column(name = "engine_version", columnDefinition = "VARCHAR(45)", nullable = false)
    @JsonProperty(value = "Engine_version")
    private String engineVersion;

    @Column(name = "css_grade", columnDefinition = "VARCHAR(5)", nullable = false)
    @JsonProperty(value = "CSS_grade")
    private String CSSGrade;

}
