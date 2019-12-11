import * as Yup from 'yup';
import AbstractValidation from './AbstractValidation';

class AddPlanetValidation extends AbstractValidation {
  protected rules(): object {
    return {
      name: Yup
        .string()
        .required('O nome do planeta não pode ser vazio.')
        .min(4, 'O nome do planeta deve ter no mínimo 4 caracteres.'),
      climate: Yup
        .string()
        .required('O clima do planete não pode ser vazio.')
        .min(4, 'O clima do planeta deve ter no mínimo 4 caracteres.'),
      terrain: Yup
        .string()
        .required('O terreno do planeta não pode ser vazio.')
        .min(4, 'O tipo de terreno tem que ter no mínimo 4 caracteres.'),
    };
  }
}

export default new AddPlanetValidation();
