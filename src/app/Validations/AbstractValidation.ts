import * as Yup from 'yup';
import { Request } from 'express';

interface ValidationErrors {
  errors: Array<string>
}

abstract class AbstractValidation {
  /**
   * Método reponsável por validar os dados de um objeto
   *
   * @param req Request
   */
  public async validate(req:Request): Promise<ValidationErrors> {
    try {
      const schema = Yup.object().shape(this.rules());
      await schema.validate(req.body, { abortEarly: false });
      return { errors: [] };
    } catch (err) {
      return { errors: err.errors };
    }
  }

  protected abstract rules(): object;
}

export default AbstractValidation;
