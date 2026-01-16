# 🎓 Sistema de Registro de Estudiantes
## Proyecto Integrador - Calidad de Software

[![CI Pipeline](https://github.com/StevenCarrilloLoor/student-registry-quality/actions/workflows/ci.yml/badge.svg)](https://github.com/StevenCarrilloLoor/student-registry-quality/actions)
![Tests](https://img.shields.io/badge/tests-99%20passing-brightgreen)
![Coverage](https://img.shields.io/badge/coverage-95%25-brightgreen)
![Java](https://img.shields.io/badge/Java-11-orange)

---

## 📋 Descripción

Sistema de gestión de estudiantes desarrollado como proyecto integrador para demostrar la aplicación de:
- ✅ **Principios SOLID** (todos los 5)
- ✅ **Clean Code** (código limpio y legible)
- ✅ **Análisis estático** (Checkstyle, PMD)
- ✅ **Cobertura de pruebas** (JaCoCo >95%)
- ✅ **CI/CD** (GitHub Actions)

---

## 🏗️ Arquitectura

### Estructura del Proyecto
```
src/main/java/com/student/
├── interfaces/              # ISP - Interfaces segregadas
│   ├── Gradable.java
│   ├── Identifiable.java
│   ├── Statusable.java
│   └── Reportable.java
├── model/                   # SRP, LSP - Modelos de dominio
│   ├── BaseStudent.java
│   ├── RegularStudent.java
│   └── HonorsStudent.java
├── repository/              # DIP - Abstracción de persistencia
│   ├── StudentRepository.java
│   └── InMemoryStudentRepository.java
├── service/                 # SRP, DIP - Lógica de negocio
│   └── StudentService.java
├── validation/              # OCP - Validadores extensibles
│   ├── StudentValidator.java
│   ├── MinimumGradeValidator.java
│   ├── MinimumNameLengthValidator.java
│   └── CompositeValidator.java
├── report/                  # ISP - Generación de reportes
│   └── ReportGenerator.java
└── StudentManager.java      # Punto de entrada
```

---

## 🎯 Principios SOLID Aplicados

### ✅ SRP - Single Responsibility Principle
- **Student:** Solo maneja datos del estudiante
- **StudentService:** Solo lógica de negocio
- **StudentRepository:** Solo persistencia

### ✅ OCP - Open/Closed Principle
- Sistema de **validadores extensibles**
- Agregar nuevos validadores sin modificar código existente
- **CompositeValidator** permite composición

### ✅ LSP - Liskov Substitution Principle
- **RegularStudent** y **HonorsStudent** son sustituibles
- Jerarquía correcta con **BaseStudent**
- Polimorfismo sin efectos secundarios

### ✅ ISP - Interface Segregation Principle
- Interfaces **pequeñas y específicas**
- `Gradable`, `Identifiable`, `Statusable`, `Reportable`
- Clases solo implementan lo necesario

### ✅ DIP - Dependency Inversion Principle
- Dependencia de **abstracciones** (`StudentRepository`)
- **Inyección de dependencias** en constructores
- Fácil intercambiar implementaciones

---

## 📊 Métricas de Calidad

### Comparativa ANTES vs DESPUÉS

| Métrica | ANTES | DESPUÉS | Mejora |
|---------|-------|---------|--------|
| **Cobertura de código** | 0% | 95%+ | ✅ +95% |
| **Pruebas unitarias** | 1 | 99 | ✅ +98 |
| **Violaciones Checkstyle** | ~15 | 0-2 | ✅ 98% |
| **Violaciones PMD** | ~10 | 0-1 | ✅ 95% |
| **Clases** | 1 | 17 | ✅ +16 |
| **Líneas de código** | ~15 | ~2000 | ✅ +1985 |

---

## 🚀 Compilar y Ejecutar

### Requisitos
- **Java 11+**
- **Maven 3.6+**

### Comandos
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java

# Ejecutar todas las pruebas
mvn test

# Generar reportes de calidad
mvn clean test checkstyle:checkstyle pmd:pmd jacoco:report

# Ver reportes
# Checkstyle: target/site/checkstyle.html
# PMD: target/site/pmd.html
# JaCoCo: target/site/jacoco/index.html
```

---

## 🧪 Pruebas

### Distribución de Pruebas
```
Total: 99 pruebas unitarias

├── StudentTest ............................ 14 tests
├── StudentManagerTest ..................... 16 tests
├── RegularStudentTest ..................... 8 tests
├── HonorsStudentTest ...................... 10 tests
├── InMemoryStudentRepositoryTest .......... 11 tests
├── StudentServiceTest ..................... 12 tests
├── ReportGeneratorTest .................... 7 tests
├── InterfaceSegregationTest ............... 6 tests
├── MinimumGradeValidatorTest .............. 4 tests
├── MinimumNameLengthValidatorTest ......... 4 tests
└── CompositeValidatorTest ................. 7 tests
```

### Ejecutar pruebas específicas
```bash
# Solo pruebas de modelo
mvn test -Dtest="*Student*Test"

# Solo pruebas de validación
mvn test -Dtest="*Validator*Test"

# Solo pruebas de repositorio
mvn test -Dtest="*Repository*Test"
```

---

## 📚 Herramientas Utilizadas

| Herramienta | Propósito | Configuración |
|-------------|-----------|---------------|
| **Maven** | Gestión de dependencias | `pom.xml` |
| **JUnit 5** | Testing | `junit-jupiter:5.9.3` |
| **Checkstyle** | Estilo de código | `config/checkstyle.xml` |
| **PMD** | Detección de defectos | `config/pmd-ruleset.xml` |
| **JaCoCo** | Cobertura de código | Plugin Maven |
| **GitHub Actions** | CI/CD | `.github/workflows/ci.yml` |

---

## 🔄 Pipeline CI/CD

El proyecto incluye un pipeline automatizado que ejecuta en cada push:

1. ✅ Compilación del código
2. ✅ Ejecución de todas las pruebas
3. ✅ Análisis de estilo (Checkstyle)
4. ✅ Análisis de defectos (PMD)
5. ✅ Generación de reporte de cobertura (JaCoCo)
6. ✅ Publicación de artifacts

**Ver ejecuciones:** [GitHub Actions](https://github.com/TU-USUARIO/student-registry-quality/actions)

---

## 📖 Documentación

### Documentos del Proyecto

- **[Análisis Inicial](docs/ANALYSIS-INITIAL.md)** - Estado del código ANTES
- **[Reporte de Comparación](docs/COMPARISON-REPORT.md)** - ANTES vs DESPUÉS
- **Reportes ANTES:** `docs/before/`
- **Reportes DESPUÉS:** `docs/after/`

### Ejemplo de Uso
```java
// Crear repositorio y servicio (DIP)
StudentRepository repository = new InMemoryStudentRepository();
StudentService service = new StudentService(repository);

// Agregar estudiantes (LSP - diferentes tipos)
service.addStudent(new RegularStudent("John Doe", 85.5));
service.addStudent(new HonorsStudent("Jane Smith", 88.0, 5.0));

// Obtener promedio (usa Gradable interface - ISP)
double average = service.getAverageGrade();
System.out.println("Average: " + average); // 89.25

// Filtrar por estado (usa Statusable interface - ISP)
List honors = service.getStudentsByStatus("Honors");
```

---

## 👥 Equipo

**Group 2 - Martes**

| Rol | Responsabilidad |
|-----|-----------------|
| **Líder del equipo** | Coordinación y supervisión |
| **Análisis de métricas** | Definición e interpretación de métricas |
| **Revisión manual** | Aplicación de Clean Code |
| **Análisis estático** | Configuración de herramientas |

---

## 📅 Cronograma de Desarrollo

- ✅ **Fase 1:** Setup inicial y código problemático
- ✅ **Fase 2:** Aplicar SRP (Single Responsibility)
- ✅ **Fase 3:** Aplicar OCP (Open/Closed)
- ✅ **Fase 4:** Aplicar LSP (Liskov Substitution)
- ✅ **Fase 5:** Aplicar ISP (Interface Segregation)
- ✅ **Fase 6:** Aplicar DIP (Dependency Inversion)
- ✅ **Fase 7:** Pruebas unitarias completas
- ✅ **Fase 8:** CI/CD y reportes finales
- ✅ **Fase 9:** Documentación final

**Tiempo total:** ~6 horas  
**Commits realizados:** 11+  
**Fecha de entrega:** 18 de enero de 2026

---

## 🎓 Lecciones Aprendidas

### ¿Qué funcionó bien?
- ✅ Aplicar SOLID paso a paso con commits incrementales
- ✅ Escribir pruebas desde el inicio
- ✅ Usar herramientas automatizadas
- ✅ Refactorizar de forma iterativa

### ¿Qué mejoraría?
- Planificar arquitectura desde el principio
- Usar TDD (Test-Driven Development)
- Más documentación en código

### ¿Qué aplicar en futuros proyectos?
- ✅ SOLID en todo el código
- ✅ CI/CD desde el día 1
- ✅ Cobertura >80% siempre
- ✅ Revisiones automáticas

---

## 📖 Referencias

- [Principios SOLID](https://en.wikipedia.org/wiki/SOLID)
- [Clean Code - Robert C. Martin](https://www.oreilly.com/library/view/clean-code-a/9780136083238/)
- [Checkstyle Documentation](https://checkstyle.sourceforge.io/)
- [PMD Documentation](https://pmd.github.io/)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/)

---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos para el curso ISWZ3208 - Calidad de Software.

---

## 🏆 Resultados Finales
```
✅ 99 pruebas unitarias - TODAS PASANDO
✅ 95%+ cobertura de código
✅ 0 violaciones de Checkstyle
✅ 0 violaciones de PMD
✅ Todos los principios SOLID aplicados
✅ CI/CD funcionando correctamente
✅ Código limpio y profesional
```

---

**Elaborado por:** Group 2 - Martes  
**Curso:** ISWZ3208 - Calidad de Software  
**Institución:** UDLA 
**Fecha:** 16 de enero de 2026

---

⭐ **Si te gustó este proyecto, dale una estrella en GitHub!**