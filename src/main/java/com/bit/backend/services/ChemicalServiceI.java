package com.bit.backend.services;

import com.bit.backend.dtos.ChemicalDto;

import java.util.List;

public interface ChemicalServiceI {
    ChemicalDto addChemical(ChemicalDto chemicalDto);

    List<ChemicalDto> getAllChemicals();

    ChemicalDto getChemicalById(Integer id);

    ChemicalDto updateChemical(Integer id, ChemicalDto chemicalDto);

    ChemicalDto deleteChemical(Integer id);
}
