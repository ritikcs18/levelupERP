package com.levelup.erp.material.controller;
import com.levelup.erp.material.dto.FinishedMaterialDTO;
import com.levelup.erp.material.service.FinishedMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/materials")
public class FinishedMaterialController {

    private final FinishedMaterialService finishedMaterialService;

    public FinishedMaterialController(FinishedMaterialService finishedMaterialService) {
        this.finishedMaterialService = finishedMaterialService;
    }


    @GetMapping("/view")
    public String viewAllMaterials(Model model) {
        List<FinishedMaterialDTO> materials = finishedMaterialService.getAllMaterials();
        model.addAttribute("materials", materials);
        return "material/view"; // your Thymeleaf HTML
    }

    @GetMapping("/add")
    public String showAddMaterialForm(Model model) {
        model.addAttribute("finishedMaterialDTO", new FinishedMaterialDTO());
        return "material/addMaterial";
    }

    @PostMapping("/add")
    public String addMaterial(@ModelAttribute("finishedMaterialDTO") FinishedMaterialDTO finishedMaterialDTO,
                            RedirectAttributes redirectAttributes) {
        finishedMaterialService.saveFromMaterialDTO(finishedMaterialDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Material added successfully!");
        return "redirect:/materials/add";
    }


    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        return "material/deleteMaterial";
    }



    @PostMapping("/delete")
    public String deleteMaterial(@RequestParam("headerMaterial") String headerMaterial, Model model) {
        try {
            finishedMaterialService.deleteByHeaderMaterial(headerMaterial);
            model.addAttribute("deleteSuccess", true);
        } catch (Exception e) {
            model.addAttribute("deleteError", "Header Material not found or could not be deleted.");
        }
        return "material/deleteMaterial";
    }


}
