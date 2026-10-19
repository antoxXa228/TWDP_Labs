# TWDP OpenGL Labs

Computer graphics laboratory assignments built with Java and JOGL (OpenGL 2.x).

---

## Lab 1: Lighting, Transformations & Heightmap Terrain

Implementation of a basic 3D interactive scene featuring custom geometry, lighting controls, and displacement-based terrain modeling.

### Features
* **Circling Light Source:** Orbiting light (`GL_LIGHT0`) with dynamic position recalculation via trigonometric functions. Can be toggled on/off.
* **Custom Model Geometry:** Added chimney geometry using quad primitives (`GL_QUADS`) with proper surface normals to match the scene's diffuse lighting model.
* **Model Transformations:** Global matrix translation and localized object rotation using `glRotatef` around the Y-axis.
* **Interactive Lighting:** Dynamic diffuse/specular color switching between red, green, and blue presets, with a fallback to the default white light.
* **Heightmap Terrain (`GroundRetro`):** Grid-based terrain generated via sampling grayscale values from `DisplacementMap.png`, applying variable vertex heights ($Y$) across a subdivided quad plane.

---

### Screenshot

<img width="778" height="587" alt="image" src="https://github.com/user-attachments/assets/69a0206d-39f8-477e-aadf-e9ce13de575e" />


---

### Controls

| Key | Action |
| :--- | :--- |
| **W, S, A, D** | Move camera forward / backward / left / right |
| **Mouse Drag** | Look around / pitch & yaw |
| **L** | Toggle orbiting light movement |
| **1** | Switch light color to Red |
| **2** | Switch light color to Green |
| **3** | Switch light color to Blue |
| **0** | Reset light color to Default (White) |
| **R** | Toggle Wireframe / Solid render mode |

---

### Tech Stack & Prerequisites
* **Java 17** (or compatible JDK)
* **JOGL 2.3.2** (with matching native libraries for `gluegen-rt` and `jogl-all`)
* **IntelliJ IDEA** / NetBeans / Eclipse

### How to Run
1. Open the project in IntelliJ IDEA.
2. Ensure JOGL jars (`gluegen-rt.jar`, `jogl-all.jar`) and the native binaries folder (`natives-amd64`) are correctly referenced in the project dependencies.
3. Run `src/ksg/WorldMain.java`.
